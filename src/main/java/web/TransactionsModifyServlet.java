/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import biz.manager.CategoryMgr;
import biz.manager.TransactionsMgr;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Transactions;
import utils.DateUtils;

/**
 *
 * @author Guest
 */
@WebServlet("/modifyTransaction")
public class TransactionsModifyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    TransactionsMgr transactionsManager;

    @EJB
    CategoryMgr categoryManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Transactions transaction = this.transactionsManager.getTransaction(
                Integer.parseInt(req.getParameter("transactionId")));
        List<Category> categoriesList = this.categoryManager.getCategoriesList();
        req.setAttribute("transaction", transaction);
        req.setAttribute("categoriesList", categoriesList);
        req.getRequestDispatcher("/WEB-INF/jsp/modifyTransaction.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Date transactionDate = DateUtils.comboDate(Integer.parseInt(req.getParameter("transactionYear")),
                req.getParameter("transactionMonth"), Integer.parseInt(req.getParameter("transactionDay")));
        Date transactionEndDate = DateUtils.comboDate(Integer.parseInt(req.getParameter("transactionEndYear")),
                req.getParameter("transactionEndMonth"), Integer.parseInt(req.getParameter("transactionEndDay")));
        Category category = this.categoryManager.getCategory(req.getParameter("categoryLabel"));

        if (req.getParameter("yesBtn") != null) {
            this.transactionsManager.modify(
                    Integer.parseInt(req.getParameter("transactionId")),
                    transactionDate,
                    transactionEndDate,
                    req.getParameter("transactionLabel"),
                    Double.parseDouble(req.getParameter("transactionAmount")),
                    category
            );
        }

        req.getRequestDispatcher("/WEB-INF/jsp/displayTransactions.jsp").forward(req, resp);

    }

}
