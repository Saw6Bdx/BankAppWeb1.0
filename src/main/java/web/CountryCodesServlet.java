package web;

import biz.exception.CountryCodeAlreadyExistingException;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.manager.CountryCodeMgr;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import model.CountryCode;

@WebServlet({"/countryCodes"})
//@WebServlet({"/countryCodes", "/index.html"})
public class CountryCodesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private CountryCodeMgr countryCodeManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/createCountryCode.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            CountryCode countryCode = countryCodeManager.save(req.getParameter("code"));
            resp.sendRedirect(req.getContextPath() + "/CountryCode?code=" + countryCode.getCode());
        } catch (CountryCodeAlreadyExistingException ex) {
            log("Country code already existing in the database", ex);
            req.setAttribute("error", "countrycode.already.exists");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/createCountryCode.jsp").forward(req, resp);
        }
    }
}
