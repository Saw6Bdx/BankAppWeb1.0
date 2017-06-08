package web;

import biz.exception.NoAccountAvailableException;
import biz.manager.AccountMgr;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

@WebServlet({"/accountDisplay"})
public class AccountDisplayServlet
  extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  @EJB
  AccountMgr accountManager;
  
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    try
    {
      List<Account> accountList = this.accountManager.displayAccount(Integer.parseInt(req.getParameter("holderId")));
      req.setAttribute("accountList", accountList);
      req.getRequestDispatcher("/WEB-INF/jsp/displayAccount.jsp").forward(req, resp);
    }
    catch (NoAccountAvailableException ex)
    {
      log("No account available", ex);
      req.setAttribute("error", "no.account.available");
      getServletContext().getRequestDispatcher("/WEB-INF/jsp/displayAccount.jsp").forward(req, resp);
      Logger.getLogger(AccountDisplayServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}