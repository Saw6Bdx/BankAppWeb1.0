package web;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/log"})
public class LogServlet
  extends HttpServlet
{
  private static final long serialVersionUID = 7446985734933559486L;
  
  public void init()
    throws ServletException
  {
    System.out.println("################################# init " + getServletName());
  }
  
  public void destroy()
  {
    System.out.println("################################# destroy " + getServletName());
  }
  
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    resp.setCharacterEncoding("utf-8");
    resp.setContentType("text/plain");
    resp.getWriter().write(getServletName() + " called successfully");
  }
}
