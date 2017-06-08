package web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.exception.NoAccountAvailableException;
import biz.exception.NoAgencyAvailableException;
import biz.exception.NoCountryCodeAvailableException;
import biz.manager.AccountMgr;
import model.Account;
import model.AccountType;
import model.Agency;
import model.CountryCode;
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
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		if (req.getParameter("applyBtn") != null) {

			Date creationDate = DateUtils.comboDate(Integer.parseInt(req.getParameter("userYear")),
					req.getParameter("userMonth"), Integer.parseInt(req.getParameter("userDay")));

			// Creation of objects ...
			// ...accountType
			AccountType accountType = new AccountType(Integer.parseInt(req.getParameter("accountTypeId")));
			
			// ...countryCode
			CountryCode countryCode = new CountryCode(Integer.parseInt(req.getParameter("countryCodeId")));
			
			// ...category
			Agency agency = new Agency(Integer.parseInt(req.getParameter("agencyId")));

			// ...transactions
			Account account = new Account(null, req.getParameter("number"), creationDate,
					Double.parseDouble(req.getParameter("firstBalance")), Double.parseDouble(req.getParameter("overdraft")));
			account.setIdAccountType(accountType);
			account.setIdCountryCode(countryCode);
			account.setIdAgency(agency);
			

			this.accountManager.createAccount(account);

			resp.sendRedirect(req.getContextPath() + "/");
		} else {
			// REDIRECTION VERS LA PAGE D'ACCUEIL, HORS CONNEXION
			resp.sendRedirect(req.getContextPath() + "/");
		}
	}

	

}
