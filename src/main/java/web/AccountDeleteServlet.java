/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import biz.manager.AccountMgr;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Guest
 */
@WebServlet("/deleteAccount")
public class AccountDeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    AccountMgr accountManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/jsp/deleteAccount.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        if (req.getParameter("yesBtn") != null) {
            try {
                this.accountManager.delete(req.getParameter("accountId"));
            } catch (IllegalStateException ex) {
                Logger.getLogger(AccountDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        resp.sendRedirect(req.getContextPath() + "/accountDisplay?holderId=" + req.getParameter("holderId"));
        
    }

}
