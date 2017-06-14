package web;

import biz.exception.CategoryAlreadyExistingException;
import biz.manager.CategoryMgr;
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
    private CategoryMgr categoryManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/createCategory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            this.categoryManager.save(req.getParameter("categoryLabel"));
            resp.sendRedirect(req.getContextPath() + "/displayCategories?holderId=" + Integer.parseInt(req.getParameter("holderId")) + "&accountId=" + Integer.parseInt(req.getParameter("accountId")));
        } catch (CategoryAlreadyExistingException ex) {
            log("Category already exists", ex);
            req.setAttribute("error", "category.already.exists");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/createCategory.jsp").forward(req, resp);
        }

    }

}
