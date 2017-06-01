/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import biz.manager.HolderManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Address;
import model.Holder;
import model.Postcode;

/**
 *
 * @author Guest
 */
@WebServlet({"/userCreation","/index.html"})
public class HolderNewServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @EJB
    HolderManager holderManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/createUser.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        System.out.println("doPost(HolderNewServlet");
        
        if (req.getParameter("applyBtn") != null) {
            
            System.out.println("doPost(HolderNewServlet): creation d'un compte utilisateur");
            // Creation of objects ...
            
            // ... postcode
            System.out.println(req.getParameter("userPostCode"));
            Postcode postcode = new Postcode(null,
                    Integer.parseInt(req.getParameter("userPostCode")),
                    req.getParameter("userCity")
            );
            
            // ... address
            Address address = new Address(null,
                    req.getParameter("userAddressLine1")
            );
            address.setLine2(req.getParameter("userAddressLine2"));
            address.setIdPostcode(postcode);
            
            // ...holder
            Holder holder = new Holder(null,
                    req.getParameter("userName"),
                    req.getParameter("userFirstName"),
                    req.getParameter("userLogin"),
                    req.getParameter("userPassword")
            );
            /* String userBirthday = req.getParameter("userBirthday");
            holder.setBirthday(userBirthday);*/
            holder.setPhone(req.getParameter("userPhone"));
            holder.setIdAddress(address);
            
            this.holderManager.createUser(holder, address, postcode);
          
            resp.sendRedirect(req.getContextPath() + "/account");
        }
        
        else {
            // REDIRECTION VERS LA PAGE D'ACCUEIL, HORS CONNEXION
            resp.sendRedirect(req.getContextPath() + "/transactions");
        }
        
        
    }

    
    
}
