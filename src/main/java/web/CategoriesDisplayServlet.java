package web;

import biz.exception.CategoryAlreadyExistingException;
import biz.exception.NoCategoriesAvailableException;
import biz.exception.NoTransactionsAvailableException;
import biz.manager.CategoryManager;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;

@WebServlet("/displayCategories")
public class CategoriesDisplayServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private CategoryManager categoryManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        double[] percentByCategories, amount;
        double sum;
        List<Category> categoriesList;
        try {
            percentByCategories = this.categoryManager.calculatePercentByCategories();
            amount = this.categoryManager.getAmount();
            categoriesList = this.categoryManager.getCategoriesList();
            sum = this.categoryManager.getSum();
            
            req.setAttribute("sum", sum);
            req.setAttribute("amount", amount);
            req.setAttribute("percentByCategories", percentByCategories);
            req.setAttribute("categoriesList", categoriesList);

            req.getRequestDispatcher("/WEB-INF/jsp/displayCategories.jsp").forward(req, resp);
        } catch (NoCategoriesAvailableException ex) {
            Logger.getLogger(CategoriesDisplayServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoTransactionsAvailableException ex) {
            Logger.getLogger(CategoriesDisplayServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            String categoryLabel = req.getParameter("categoryLabel");
            Category category = this.categoryManager.save("CategoriesServletL35");
            System.out.println("doPost(CategoriesServlet) - l36: " + categoryLabel);
            resp.sendRedirect(req.getContextPath() + "/category?categoryLabel=" + category.getLabel());
        } catch (CategoryAlreadyExistingException ex) {
            req.setAttribute("error", "category.already.exists");
            doGet(req, resp);
        }
    }
}
