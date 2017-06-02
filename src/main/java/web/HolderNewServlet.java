/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import biz.exception.LoginAlreadyExistingException;
import biz.exception.PasswordsNotIdenticalException;
import biz.manager.HolderManager;
import java.io.IOException;
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
//@WebServlet({"/userCreation", "/index.html"})
@WebServlet({"/userCreation"})
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

        req.setCharacterEncoding("UTF-8");

        if (req.getParameter("applyBtn") != null) {

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

            try {
                this.holderManager.createUser(holder, address, postcode, req.getParameter("userPasswordConfirmation"));
            } catch (LoginAlreadyExistingException ex) {
                log("Login already existing in the database", ex);
                req.setAttribute("error", "login.already.exists");
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/createUser.jsp").forward(req, resp);
            } catch (PasswordsNotIdenticalException ex) {
                log("Passwords are not identical", ex);
                req.setAttribute("error", "pwd.not.identical");
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/createUser.jsp").forward(req, resp);
            }

            resp.sendRedirect(req.getContextPath() + "/account");
        } else {
            // REDIRECTION VERS LA PAGE D'ACCUEIL, HORS CONNEXION
            resp.sendRedirect(req.getContextPath() + "/account");
        }

    }

}
