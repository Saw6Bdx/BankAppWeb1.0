package web;

import biz.manager.AccountManager;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/accounts"})
public class AccountsServlet
  extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  @EJB
  private AccountManager accountManager;
  
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    getServletContext().getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp").forward(req, resp);
  }
  
  protected void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    throw new Error("Unresolved compilation problem: \n\tThe method save(String, Date, double, double) in the type AccountManager is not applicable for the arguments (String, String, String, String)\n");
  }
}
