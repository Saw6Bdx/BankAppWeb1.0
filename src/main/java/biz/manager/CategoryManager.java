package biz.manager;

import biz.exception.CategoryDoesNotExistException;
import biz.exception.CategoryAlreadyExistingException;
import biz.exception.NoCategoriesAvailableException;
import biz.exception.NoTransactionsAvailableException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Category;
import model.Transactions;

@Stateless
public class CategoryManager {

    @PersistenceContext(unitName = "BankAppPU")
    private EntityManager em;

    private final List<Category> categories = new ArrayList<>();
    private List<Category> categoriesList = new ArrayList<>();
    private List<Transactions> transactionsList = new ArrayList<>();

    double sum = 0.0;
    double[] tabSumCategory;

    public CategoryManager() {
    }

    @Lock(LockType.WRITE) // pas obligé, LockType par défault
    public Category save(String label) throws CategoryAlreadyExistingException {
        for (Category category : categories) {
            if (category.getLabel().equals(label)) {
                throw new CategoryAlreadyExistingException();
            }
        }

        Category newCategory = new Category(null, label);//"CategoryManagerL33");
        em.persist(newCategory);

        categories.add(newCategory);
        return newCategory;
    }

    @Lock(LockType.READ)
    public Category getByLabel(String label) throws CategoryDoesNotExistException {
        for (Category category : categories) {
            if (category.getLabel().equals(label)) {
                return category;
            }
        }
        throw new CategoryDoesNotExistException();
    }

    public List<Category> getCategoriesList() {
        return this.categoriesList;
    }

    @Lock(LockType.READ)
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
