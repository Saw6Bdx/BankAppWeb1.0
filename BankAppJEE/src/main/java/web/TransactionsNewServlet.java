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
import biz.exception.NoCategoriesAvailableException;
import biz.exception.NoTransactionsAvailableException;
import biz.manager.AccountMgr;
import biz.manager.CategoryMgr;
import biz.manager.TransactionsMgr;
import model.Account;
import model.Category;
import model.TransactionType;
import model.Transactions;
import utils.DateUtils;

/**
 *
 * @author Guest
 */
@WebServlet({ "/transactionsCreation" })
public class TransactionsNewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	TransactionsMgr transactionsManager;
	@EJB
	AccountMgr accountManager;
	@EJB
	CategoryMgr categoryManager;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Account> accountList = this.accountManager.displayAccount();
			req.setAttribute("accountList", accountList);
			List<TransactionType> transactionTypeList = this.transactionsManager.displayTransactionType();
			req.setAttribute("transactionTypeList", transactionTypeList);
			List<Category> categoryList = this.categoryManager.displayCategories();
			req.setAttribute("categoryList", categoryList);
			req.getRequestDispatcher("/WEB-INF/jsp/createTransactions.jsp").forward(req, resp);
		} catch (

		NoTransactionsAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoAccountAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoCategoriesAvailableException e) {
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
			Date endDate = DateUtils.comboDate(Integer.parseInt(req.getParameter("userEndYear")),
					req.getParameter("userEndMonth"), Integer.parseInt(req.getParameter("userEndDay")));

			// Creation of objects ...
			// ...account
			Account account = new Account(Integer.parseInt(req.getParameter("accountId")));
			
			// ...transactionType
			TransactionType transactionType = new TransactionType(Integer.parseInt(req.getParameter("transactionTypeId")));
			
			// ...category
			Category category = new Category(Integer.parseInt(req.getParameter("categoryId")));

			// ...transactions
			Transactions transactions = new Transactions(null, req.getParameter("label"),
					Double.parseDouble(req.getParameter("amount")), creationDate, endDate);
			transactions.setIdAccount(account);
			transactions.setIdTransactionType(transactionType);
			transactions.setIdCategory(category);

			this.transactionsManager.createTransactions(transactions);

			resp.sendRedirect(req.getContextPath() + "/");
		} else {
			// REDIRECTION VERS LA PAGE D'ACCUEIL, HORS CONNEXION
			resp.sendRedirect(req.getContextPath() + "/");
		}
	}

}
