package web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.exception.LoginAlreadyExistingException;
import biz.exception.NoAccountAvailableException;
import biz.exception.NoTransactionsAvailableException;
import biz.exception.PasswordsNotIdenticalException;
import biz.manager.AccountManager;
import biz.manager.TransactionsManager;
import model.Account;
import model.TransactionType;

/**
 *
 * @author Guest
 */
@WebServlet({"/transactionsCreation"})
public class TransactionsNewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	TransactionsManager transactionsManager;
	@EJB
	AccountManager accountManager;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		try {
			List<Account> accountList = this.accountManager.displayAccount();
		    req.setAttribute("accountList", accountList);
			List<TransactionType> transactionTypeList = this.transactionsManager.displayTransactionType();
			req.setAttribute("transactionTypeList", transactionTypeList);
			req.getRequestDispatcher("/WEB-INF/jsp/createTransactions.jsp").forward(req, resp);
		} catch (NoTransactionsAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoAccountAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
