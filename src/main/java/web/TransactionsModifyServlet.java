/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import biz.manager.TransactionsMgr;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            req.getRequestDispatcher("/WEB-INF/jsp/modifyTransaction.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Date transactionDate = DateUtils.comboDate(Integer.parseInt(req.getParameter("transactionYear")),
					req.getParameter("transactionMonth"), Integer.parseInt(req.getParameter("transactionDay")));
        
        if (req.getParameter("yesBtn") != null) {
            this.transactionsManager.modify(req.getParameter("transactionId"),
                    transactionDate,
                    req.getParameter("transactionLabel"),
                    req.getParameter("transactionAmount"),
                    req.getParameter("transactionIdCategory")
            );
        }

        req.getRequestDispatcher("/WEB-INF/jsp/displayTransactions.jsp").forward(req, resp);

    }

}
