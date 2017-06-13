/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import biz.manager.AccountMgr;
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
import model.Account;
import model.AccountType;
import model.Address;
import model.Agency;
import model.Bank;
import model.Category;
import model.CountryCode;
import model.Postcode;
import model.Transactions;
import utils.DateUtils;

/**
 *
 * @author Guest
 */
@WebServlet("/modifyAccount")
public class AccountModifyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    AccountMgr accountManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Account account = this.accountManager.getAccount(Integer.parseInt(req.getParameter("accountId")));
        Agency agency = account.getIdAgency();
        Address address = agency.getIdAddress();
        Postcode postcode = address.getIdPostcode();
        Bank bank = agency.getIdBank();
        AccountType accountType = account.getIdAccountType();
        CountryCode countryCode = account.getIdCountryCode();
       
        req.setAttribute("account", account);
        req.setAttribute("agency", agency);
        req.setAttribute("address", address);
        req.setAttribute("postcode", postcode);
        req.setAttribute("bank", bank);
        req.setAttribute("accountType", accountType);
        req.setAttribute("countryCode", countryCode);
       
        req.getRequestDispatcher("/WEB-INF/jsp/modifyAccount.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*Date transactionDate = DateUtils.comboDate(Integer.parseInt(req.getParameter("transactionYear")),
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

        resp.sendRedirect(req.getContextPath()
                + "/transactionsDisplay?holderId=" + req.getParameter("holderId")
                + "&accountId=" + req.getParameter("accountId"));*/

    }

}
