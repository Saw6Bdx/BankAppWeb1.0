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

import biz.exception.NoBankAvailableException;
import biz.manager.BankMgr;
import model.AccountManager;
import model.Address;
import model.Agency;
import model.Bank;
import model.Postcode;
import utils.DateUtils;

/**
 *
 * @author Guest
 */
@WebServlet({ "/bankCreation" })
public class BankNewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	BankMgr bankManager;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Bank> bankList = this.bankManager.displayBank();
			req.setAttribute("bankList", bankList);
			req.getRequestDispatcher("/WEB-INF/jsp/createBank.jsp").forward(req, resp);
		} catch (NumberFormatException e) {
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

		if (req.getParameter("applyBtn") != null) {

			Date assignmentDate = DateUtils.comboDate(Integer.parseInt(req.getParameter("managerYear")),
					req.getParameter("managerMonth"), Integer.parseInt(req.getParameter("managerDay")));

			// Creation of objects ...
			// ... postcode
			Postcode postcode = new Postcode(null, Integer.parseInt(req.getParameter("agencyPostCode")),
					req.getParameter("agencyCity"));

			// ... address
			Address address = new Address(null, req.getParameter("agencyAddressLine1"));
			address.setLine2(req.getParameter("agencyAddressLine2"));
			address.setIdPostcode(postcode);

			// ...bank
			Bank bank = new Bank(Integer.parseInt(req.getParameter("bankId")));

			// ...agency
			Agency agency = new Agency(null, req.getParameter("agencyName"), req.getParameter("agencyCode"));
			agency.setIdAddress(address);
			agency.setIdBank(bank);

			// ...accountManager
			AccountManager accountManager = new AccountManager(null, req.getParameter("managerName"),
					req.getParameter("managerFirstName"), assignmentDate);
			accountManager.setIdAgency(agency);

			this.bankManager.createBank(bank, agency, accountManager, address, postcode);

			resp.sendRedirect(req.getContextPath() + "/");
		} else {
			// REDIRECTION VERS LA PAGE D'ACCUEIL, HORS CONNEXION
			resp.sendRedirect(req.getContextPath() + "/");
		}
	}

}
