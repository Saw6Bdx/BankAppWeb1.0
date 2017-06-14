package web;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.exception.AccountAlreadyExistingException;
import biz.exception.NoAccountAvailableException;
import biz.exception.NoAgencyAvailableException;
import biz.exception.NoBankAvailableException;
import biz.exception.NoCountryCodeAvailableException;
import biz.exception.NoHolderAvailableException;
import biz.manager.AccountMgr;
import biz.manager.HolderMgr;
import model.Account;
import model.AccountManager;
import model.AccountType;
import model.Address;
import model.Agency;
import model.Bank;
import model.CountryCode;
import model.Holder;
import model.Postcode;
import utils.DateUtils;

/**
 *
 * @author Guest
 */
@WebServlet({"/accountCreation"})
public class AccountNewServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    AccountMgr accountManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<AccountType> accountTypeList = this.accountManager.displayAccountType();
            req.setAttribute("accountTypeList", accountTypeList);
            List<CountryCode> countryCodeList = this.accountManager.displayCountryCode();
            req.setAttribute("countryCodeList", countryCodeList);
            List<Agency> agencyList = this.accountManager.displayAgency();
            req.setAttribute("agencyList", agencyList);
            List<Bank> bankList = this.accountManager.displayBank();
            req.setAttribute("bankList", bankList);
            req.getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp").forward(req, resp);
        } catch (NoAccountAvailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoCountryCodeAvailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoAgencyAvailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoBankAvailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Date creationDate = DateUtils.comboDate(Integer.parseInt(req.getParameter("userYear")),
                req.getParameter("userMonth"), Integer.parseInt(req.getParameter("userDay")));

        Date assignmentDate = DateUtils.comboDate(Integer.parseInt(req.getParameter("managerYear")),
                req.getParameter("managerMonth"), Integer.parseInt(req.getParameter("managerDay")));

        // Creation of objects ...
        // ...accountType
        AccountType accountType = new AccountType(Integer.parseInt(req.getParameter("accountTypeId")));

        // ...countryCode
        CountryCode countryCode = new CountryCode(Integer.parseInt(req.getParameter("countryCodeId")));

        // ... postcode
        Postcode postcode = new Postcode(null, Integer.parseInt(req.getParameter("agencyPostCode")),
                req.getParameter("agencyCity"));

        // ... address
        Address address = new Address(null, req.getParameter("agencyAddressLine1"));
        address.setLine2(req.getParameter("agencyAddressLine2"));
        address.setIdPostcode(postcode);

        // ...bank
        Bank bank = null;
        if (req.getParameter("agencyId").equals("0")) {
            bank = new Bank(null, req.getParameter("bankName"), req.getParameter("bankCode"));
        } else {
            bank = new Bank(Integer.parseInt(req.getParameter("bankId")));
        }

        // ...agency
        Agency agency = null;
        if (req.getParameter("agencyId").equals("0")) {
            agency = new Agency(null, req.getParameter("agencyName"), req.getParameter("agencyCode"));
        } else {
            agency = new Agency(Integer.parseInt(req.getParameter("agencyId")));
        }

        // ...accountManager
        AccountManager accountManager = new AccountManager(null, req.getParameter("managerName"),
                req.getParameter("managerFirstName"), assignmentDate);
        if (req.getParameter("managerPhone") != "") {
            accountManager.setPhone(req.getParameter("managerPhone"));
        }
        if (req.getParameter("managerEmail") != "") {
            accountManager.setEmail(req.getParameter("managerEmail"));
        }
        accountManager.setIdAgency(agency);

        // ...transactions
        Account account = new Account(null, req.getParameter("number"), creationDate,
                Double.parseDouble(req.getParameter("firstBalance")),
                Double.parseDouble(req.getParameter("overdraft")));
        account.setDescription(req.getParameter("description"));
        account.setInterestRate(Double.parseDouble(req.getParameter("interestRate")));
        account.setIdAccountType(accountType);
        account.setIdCountryCode(countryCode);
        account.setIdAgency(agency);

        // ... table ASSIGN (in Holder and Account classes)
        Holder holder = new Holder(Integer.parseInt(req.getParameter("holderId")));

        Collection<Holder> collHolder = new HashSet();
        collHolder.add(holder);
        account.setHolderCollection(collHolder);

        Collection<Account> collAccount = new HashSet();
        collAccount.add(account);
        holder.setAccountCollection(collAccount);

        try {
            this.accountManager.createAccount(account, agency, bank, accountManager, address, postcode);
        } catch (AccountAlreadyExistingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        resp.sendRedirect(
                req.getContextPath() + "/accountDisplay?holderId=" + Integer.parseInt(req.getParameter("holderId")));
    }
}
