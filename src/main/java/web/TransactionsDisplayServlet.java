/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import biz.exception.NoTransactionsAvailableException;
import biz.manager.AccountManager;
import biz.manager.TransactionsManager;
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
import model.Transactions;

/**
 *
 * @author Guest
 */
@WebServlet({"/transactionsDisplay"})
public class TransactionsDisplayServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    TransactionsManager transactionsManager;

    @EJB
    AccountManager accountManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Transactions> transactionsList;
        try {
            transactionsList = this.transactionsManager.displayTransactions(Integer.parseInt(req.getParameter("accountId")));
            req.setAttribute("transactionsList", transactionsList);
            req.setAttribute("overdraft", this.accountManager.getOverdraft(Integer.parseInt(req.getParameter("accountId"))));
            req.setAttribute("firstBalance", this.accountManager.getFirstBalance(Integer.parseInt(req.getParameter("accountId"))));
            req.setAttribute("accountId", req.getParameter("accountId"));

            req.getRequestDispatcher("/WEB-INF/jsp/displayTransactions.jsp").forward(req, resp);
        } catch (NoTransactionsAvailableException ex) {
            log("No transactions available", ex);
            req.setAttribute("error", "no.transactions.available");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/displayTransactions.jsp").forward(req, resp);
            Logger.getLogger(TransactionsDisplayServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Transactions> transactionsList;
        String param = "date"; //by default
        
        if (req.getParameter("sortCategory") != null) {
            param = "category";
        } else if (req.getParameter("sortDebit") != null) {
            param = "debit";
        } else if (req.getParameter("sortCredit") != null) {
            param = "credit";
        } else if (req.getParameter("sortDate") != null) {
            param = "date";
        }

        try {
            transactionsList = this.transactionsManager.transactionsOrderBy(
                    Integer.parseInt(req.getParameter("accountId")),
                    param
            );
            req.setAttribute("transactionsList", transactionsList);
            req.setAttribute("accountId", req.getParameter("accountId"));
            req.getRequestDispatcher("/WEB-INF/jsp/displayTransactions.jsp").forward(req, resp);
        } catch (NoTransactionsAvailableException ex) {
            Logger.getLogger(TransactionsDisplayServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
