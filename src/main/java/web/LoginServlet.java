package web;

import biz.manager.HolderManager;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Holder;
import static utils.Password.get_SHA_512_SecurePassword;

@WebServlet({"/login", "/index.html"})
public class LoginServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    @EJB
    HolderManager holderManager;
    private String login;
    private String password;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<String> loginList = this.holderManager.getLoginList();
        this.login = req.getParameter("login");
        this.password = req.getParameter("password");
        // Vérifier si login existe dans la base, sinon erreur
        if(loginList.contains(this.login)) {
            List<Holder> holderList = this.holderManager.getHolderWithLogin(this.login);
            String passwordHolder = holderList.get(0).getPassword();
            // Login existe, vérifier si le mot de passe correspond au même holder dans la base, sinon erreur
            if (get_SHA_512_SecurePassword(this.password,"1").equals(passwordHolder)) {
                int idHolder = holderList.get(0).getId();
                resp.sendRedirect(req.getContextPath() + "/accountDisplay?idHolder=" + idHolder);
            } else{
                req.setAttribute("error", "pwd.is.invalid");
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            } 
        } else {
            req.setAttribute("error", "login.does.not.exist");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }
}