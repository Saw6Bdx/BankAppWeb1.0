package web;

import biz.exception.CategoryAlreadyExistingException;
import biz.manager.CategoryManager;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/categoryCreation")
public class CategoryNewServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private CategoryManager categoryManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/createCategory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        if (req.getParameter("applyBtn") != null) {
            try {
                this.categoryManager.save(req.getParameter("categoryLabel"));
                req.getRequestDispatcher("/WEB-INF/jsp/displayTransactions.jsp").forward(req, resp);
            } catch (CategoryAlreadyExistingException ex) {
                log("Category already exists", ex);
                req.setAttribute("error", "category.already.exists");
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/createCategory.jsp").forward(req, resp);
            }
        }
        else {
            req.getRequestDispatcher("/WEB-INF/jsp/displayTransactions.jsp").forward(req, resp);
        }
    }

}
