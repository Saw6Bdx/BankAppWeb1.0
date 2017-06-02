package web;

import biz.manager.AccountManager;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/account"})
public class AccountServlet
  extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  @EJB
  AccountManager accountManager;
  
  protected void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    throw new Error("Unresolved compilation problem: \n\tThe method displayAccount(int) in the type AccountManager is not applicable for the arguments ()\n");
  }
}
