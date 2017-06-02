package web;

import biz.exception.CountryCodeAlreadyExistingException;
import biz.manager.CountryCodeManager;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CountryCode;

@WebServlet({"/countryCodes"})
public class CountryCodesServlet
  extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  @EJB
  private CountryCodeManager countryCodeManager;
  
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    getServletContext().getRequestDispatcher("/WEB-INF/jsp/createCountryCode.jsp").forward(req, resp);
  }
  
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    req.setCharacterEncoding("UTF-8");
    try
    {
      CountryCode countryCode = this.countryCodeManager.save(req.getParameter("code"));
      resp.sendRedirect(req.getContextPath() + "/CountryCode?code=" + countryCode.getCode());
    }
    catch (NumberFormatException nfe)
    {
      req.setAttribute("error", "invalid.amount.format");
      getServletContext().getRequestDispatcher("/WEB-INF/jsp/createCountryCode.jsp").forward(req, resp);
    }
    catch (CountryCodeAlreadyExistingException ex)
    {
      log("le compte existe déjà", ex);
      req.setAttribute("error", "account.already.exists");
      getServletContext().getRequestDispatcher("/WEB-INF/jsp/createCountryCode.jsp").forward(req, resp);
    }
  }
}
