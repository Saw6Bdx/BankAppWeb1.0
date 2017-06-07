package web;

import biz.exception.NoHolderAvailableException;
import biz.manager.HolderManager;
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
import model.Holder;

@WebServlet({"/holderDisplay", "/index.html"})
public class HolderDisplayServlet
  extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  @EJB
  HolderManager holderManager;
  
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    try
    {
      List<Holder> holdersList = this.holderManager.displayHolder();
      req.setAttribute("holdersList", holdersList);
      req.getRequestDispatcher("/WEB-INF/jsp/displayHolder.jsp").forward(req, resp);
    }
    catch (NoHolderAvailableException ex)
    {
      log("You should create a new user", ex);
      req.setAttribute("error", "no.holder.available");
      getServletContext().getRequestDispatcher("/WEB-INF/jsp/displayHolder.jsp").forward(req, resp);
      Logger.getLogger(HolderDisplayServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
