package web;

import biz.manager.HolderManager;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/login", "/index.html"})
public class LoginServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    /*@EJB
    LoginManager loginManager;*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/accountDisplay?login=" + req.getParameter("login"));
    }
    /* LoginController de l'appli java
    TypedQuery<String> qLogin = em.createQuery("SELECT h.login FROM Holder h", String.class);
        List<String> loginList = qLogin.getResultList();
        //holderList.get(i).getLogin();
        
        this.login = labelLogin.getText();
        this.password = labelPassword.getText();
        // Vérifier si login existe dans la base
        if(loginList.contains(this.login)) {
            TypedQuery<Holder> qHolder = em.createQuery("SELECT h FROM Holder h WHERE h.login =:login", Holder.class);
            List<Holder> holderList = qHolder.setParameter("login", this.login).getResultList();
            String passwordHolder = holderList.get(0).getPassword();
            // Login existe, vérifier si le mot de passe correspond au même holder dans la base
            if (get_SHA_512_SecurePassword(this.password,"1").equals(passwordHolder)) {
            //if(this.password.equals(passwordHolder)) {
                AppWindowController controller = (AppWindowController)ControllerBase.loadFxml("AppWindow.fxml", this.mediator);
                controller.setFlagHolder(holderList.get(0).getId());
                controller.initAppWindowController(mediator);
                Scene scene = new Scene(controller.getParent());
                //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                //Hide current window
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            else{
                new Alert(AlertType.ERROR, "Password is invalid.").showAndWait();
            }    
        }
        else{
            new Alert(AlertType.ERROR, "Login doesn't exist.").showAndWait();
        }

    */
}
