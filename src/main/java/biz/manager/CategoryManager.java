package biz.manager;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import biz.exception.CategoryAlreadyExistingException;
import biz.exception.CategoryDoesNotExistException;
import biz.exception.NoCategoriesAvailableException;
import biz.exception.NoTransactionsAvailableException;
import model.Category;
import model.Transactions;

@Stateless
public class CategoryManager {

    @PersistenceContext(unitName = "BankAppPU")
    private EntityManager em;

    private List<Category> categoriesList = new ArrayList<>();
    private List<Transactions> transactionsList = new ArrayList<>();

    double sum = 0.0;
    double[] tabSumCategory;

    public CategoryManager() {
    }

    public void save(String label) throws CategoryAlreadyExistingException {
        setCategoryListFromDB();
        for (Category category : categoriesList) {
            if (category.getLabel().equals(label)) {
                throw new CategoryAlreadyExistingException();
            }
        }

        Category newCategory = new Category(null, label);
        em.persist(newCategory);
    }

    public void delete(String parameter) {

        TypedQuery<Category> qCategory = this.em.createQuery("SELECT t FROM Category t WHERE t.id=:pid", Category.class);
        qCategory.setParameter("pid", Integer.parseInt(parameter));
        Category category = qCategory.getResultList().get(0);
        this.em.remove(category);

    }

    private void setCategoryListFromDB() {
        TypedQuery<Category> qCategories = this.em.createNamedQuery("Category.findAll", Category.class);
        this.categoriesList = qCategories.getResultList();
    }

    public List<Category> displayCategories() throws NoCategoriesAvailableException {
        try {
            TypedQuery<Category> qCategories = this.em.createNamedQuery("Category.findAll", Category.class);
            this.categoriesList = qCategories.getResultList();
            return this.categoriesList;
        } catch (NoResultException e) {
            throw new NoCategoriesAvailableException();
        }
    }

    public Category getByLabel(String label) throws CategoryDoesNotExistException {
        for (Category category : categoriesList) {
            if (category.getLabel().equals(label)) {
                return category;
            }
        }
        throw new CategoryDoesNotExistException();
    }

    public double[] calculatePercentByCategories() throws NoCategoriesAvailableException, NoTransactionsAvailableException {

        try {
            // Getting all the categories available
            TypedQuery<Category> qCategories = this.em.createNamedQuery("Category.findAll", Category.class);
            this.categoriesList = qCategories.getResultList();

            try {
                TypedQuery<Transactions> qTransactions = em.createQuery("SELECT b FROM Transactions b", Transactions.class);
                this.transactionsList = qTransactions.getResultList();

                // Variables setting
                int nbCategories = this.categoriesList.size();

                this.tabSumCategory = getAmountByCategories(nbCategories);

                // Percentages
                double percentage[] = new double[nbCategories + 1];
                for (int i = 0;
                        i < nbCategories + 1; i++) {
                    percentage[i] = round(tabSumCategory[i] / this.sum * 100, 2);
                }

                return percentage;

            } catch (NoResultException e) {
                throw new NoTransactionsAvailableException();
            }
        } catch (NoResultException e) {
            throw new NoCategoriesAvailableException();
        }

    }

    public double getSum() {
        return this.sum;
    }

    public double[] getAmount() {
        return this.tabSumCategory;
    }

    public List<Category> getCategoriesList() {
        return this.categoriesList;
    }

    public double[] getAmountByCategories(int nbCategories) {

        double tabSumCategory[] = new double[nbCategories + 1]; // +1 because some transactions can have no categories
        for (int i = 0; i < nbCategories; i++) { // initialization table
            tabSumCategory[i] = 0.0;
        }

        int nbTransactions = this.transactionsList.size();

        // Getting the amount of transactions by categories
        Transactions transactions = new Transactions();
        Category categories = new Category();
        double sumCat = 0.0;
        for (int j = 0; j < nbTransactions; j++) {

            transactions = this.transactionsList.get(j);
            sum += transactions.getAmount();

            for (int i = 0; i < nbCategories; i++) {

                categories = this.categoriesList.get(i);

                // Checking if the category is identical
                if (categories.equals(transactions.getIdCategory())) {
                    tabSumCategory[i] += transactions.getAmount();
                    sumCat += transactions.getAmount();
                }

            }
        }

        tabSumCategory[nbCategories] = sum - sumCat;

        return tabSumCategory;
    }

    private double round(double A, int B) {
        return (double) ((int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
    }

}
