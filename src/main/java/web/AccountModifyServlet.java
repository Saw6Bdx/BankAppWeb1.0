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
import model.CountryCode;
import model.Postcode;
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

        // Creation of all the objects ...
        // ... account
        Account account = new Account(Integer.parseInt(req.getParameter("accountId")),
                req.getParameter("accountNumber"),
                DateUtils.comboDate(Integer.parseInt(req.getParameter("accountYear")),
                        req.getParameter("accountMonth"), Integer.parseInt(req.getParameter("accountDay"))),
                Double.parseDouble(req.getParameter("accountFirstBalance")),
                Double.parseDouble(req.getParameter("accountOverdraft"))
        );
        if (!req.getParameter("accountDescription").isEmpty()) {
            account.setDescription(req.getParameter("accountDescription"));
        }
        if (!req.getParameter("accountInterestRate").isEmpty()) {
            account.setInterestRate(Double.parseDouble(req.getParameter("accountInterestRate")));
        }

        // ... countrycode 
        CountryCode countryCode = new CountryCode(null, req.getParameter("countrycodeCode"));

        // ... agency
        Agency agency = new Agency(null,
                req.getParameter("agencyName"),
                req.getParameter("agencyCode")
        );

        // ... bank
        Bank bank = new Bank(null,
                req.getParameter("bankName"),
                req.getParameter("bankCode"));

        
        // ... accountType
        AccountType accountType = new AccountType(null, req.getParameter("accounttypeType"));
        // Attention, spécifier l'id
        account.setIdAccountType(accountType);
        
        Address address = new Address(null, req.getParameter("addressLine1"));
        if (!req.getParameter("addressLine2").isEmpty()) {
            address.setLine2(req.getParameter("addressLine2")); 
        }
        
        // ... postcode
        Postcode postcode = new Postcode(null, 
                Integer.parseInt(req.getParameter("postcodePostcode")), 
                req.getParameter("postcodeCity")
        );
        
        // Modification in the database
        this.accountManager.modify(account, countryCode, agency, bank, address, postcode);

        // Redirection
        resp.sendRedirect(req.getContextPath()
                + "/accountDisplay?holderId=" + req.getParameter("holderId"));
    }

}
