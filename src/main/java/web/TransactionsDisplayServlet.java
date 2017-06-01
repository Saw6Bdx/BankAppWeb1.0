/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import biz.exception.NoTransactionsAvailableException;
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
@WebServlet({"/transactions"})
public class TransactionsDisplayServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    TransactionsManager transactionsManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<Transactions> transactions;
        try {
            transactions = this.transactionsManager.displayTransactions();
            req.setAttribute("transactions", transactions);
            req.getRequestDispatcher("/WEB-INF/jsp/displayTransactions.jsp").forward(req, resp);
        } catch (NoTransactionsAvailableException ex) {
            log("No transactions available", ex);
            req.setAttribute("error", "no.transactions.available");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/displayTransactions.jsp").forward(req, resp);
            Logger.getLogger(TransactionsDisplayServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
