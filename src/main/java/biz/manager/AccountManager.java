package biz.manager;

import biz.exception.NoAccountAvailableException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Account;

@Stateless
public class AccountManager {
    
    @PersistenceContext(unitName = "BankAppPU")
    private EntityManager em;

    private List<Account> accountList = new ArrayList<>();

    @Lock(LockType.WRITE)
    public List<Account> displayAccount() throws NoAccountAvailableException {
       
        try {
            TypedQuery<Account> qAccount = this.em.createNamedQuery("Account.findAll",Account.class);
            //TypedQuery<Account> qAccount = this.em.createNamedQuery("SELECT a FROM Account a JOIN a.holderCollection h WHERE h.id =:holder",Account.class);
            this.accountList = qAccount.getResultList();
        
            return this.accountList;
        } catch (NoResultException e) {
            throw new NoAccountAvailableException();
        }
        
    }
}
