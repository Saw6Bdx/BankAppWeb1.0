package biz.manager;

import biz.exception.LoginAlreadyExistingException;
import biz.exception.NoHolderAvailableException;
import biz.exception.PasswordsNotIdenticalException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Address;
import model.Holder;
import model.Postcode;
import static utils.Password.get_SHA_512_SecurePassword;

@Stateless
public class HolderMgr {

    @PersistenceContext(unitName = "BankAppPU")
    private EntityManager em;

    private List<Holder> holdersList = new ArrayList<Holder>();
    private List<Postcode> postcodesList = new ArrayList<Postcode>();

    public void createUser(Holder holder, Address address, Postcode postcode, String pwdConfirmation)
            throws LoginAlreadyExistingException, PasswordsNotIdenticalException, UnsupportedEncodingException {

        getHolderFromDB();

        // Check if the login is already used
        for (Holder hldr : this.holdersList) {
            if (hldr.getLogin().equals(holder.getLogin())) {
                throw new LoginAlreadyExistingException();
            }
        }

        // Check if the password and its confirmation are identical
        if (!holder.getPassword().equals(get_SHA_512_SecurePassword(pwdConfirmation, "1"))) {
            throw new PasswordsNotIdenticalException();
        }

        // Check if the postcode and the city already exist in the database
        getPostcodeFromDB();
        boolean flagPostcode = false;
        for (Postcode pc : this.postcodesList) {
            if (pc.getPostcode() == postcode.getPostcode()) {
                address.setIdPostcode(pc);
                flagPostcode = true;
            }
        }
        if (!flagPostcode) {
            this.em.persist(postcode);
        }
        
        // Creation of the user
        this.em.persist(holder);
        this.em.persist(address);

    }

    private void getHolderFromDB() {
        TypedQuery<Holder> qHolder = this.em.createNamedQuery("Holder.findAll", Holder.class);
        this.holdersList = qHolder.getResultList();
    }

    private void getPostcodeFromDB() {
        TypedQuery<Postcode> qPostcode = this.em.createNamedQuery("Postcode.findAll", Postcode.class);
        this.postcodesList = qPostcode.getResultList();
    }

    public List<String> getLoginList() {
        TypedQuery<String> qLogin = this.em.createQuery("SELECT h.login FROM Holder h", String.class);
        List<String> loginList = qLogin.getResultList();
        return loginList;
    }

    public List<Holder> getHolderWithLogin(String login) {
        TypedQuery<Holder> qHolder = this.em.createQuery("SELECT h FROM Holder h WHERE h.login =:login", Holder.class);
        List<Holder> holderList = qHolder.setParameter("login", login).getResultList();
        return holderList;
    }

    public List<Holder> displayHolder()
            throws NoHolderAvailableException {
        try {
            TypedQuery<Holder> qHolders = this.em.createNamedQuery("Holder.findAll", Holder.class);
            this.holdersList = qHolders.getResultList();

            return this.holdersList;
        } catch (NoResultException e) {
            throw new NoHolderAvailableException();
        }
    }

}