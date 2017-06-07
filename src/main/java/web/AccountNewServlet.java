package web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.exception.NoAccountAvailableException;
import biz.manager.AccountManager;
import model.Account;
import model.AccountType;

/**
 *
 * @author Guest
 */
@WebServlet({"/accountCreation"})
public class AccountNewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	AccountManager accountManager;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		try {
			List<Account> accountList = this.accountManager.displayAccount();
		    req.setAttribute("accountList", accountList);
			List<AccountType> accountTypeList = this.accountManager.displayAccountType();
			req.setAttribute("accountTypeList", accountTypeList);
			req.getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp").forward(req, resp);
		} catch (NoAccountAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}