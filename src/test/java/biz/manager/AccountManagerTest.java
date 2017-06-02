package biz.manager;

import biz.exception.AccountAlreadyExistingException;
import java.util.Date;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import model.Account;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

/**
 *
 * @author Charlotte
 */
@ManagedBean
public class AccountManagerTest extends EjbContainerTest {
    
    @EJB
    AccountManager am;

    @Test
    public void saveNewAccount() throws Exception {
        /*Account account = am.save("0987654321", new Date(0), 150.0, 200.0);
        assertNotNull(account.getId());
        assertThat(account.getNumber(), is("0987654321"));
        assertThat(account.getCreationDate(), is(new Date(0)));
        assertThat(account.getFirstBalance(), is(150.0));
        assertThat(account.getOverdraft(), is(200.0));*/
    }
    
    @Test(expected = AccountAlreadyExistingException.class)
    public void cannotSaveWhenAccountNumberAlreadyUsed() throws Exception {
        tx.begin();
        em.persist(new Account(null, "numberAlreadyExists", new Date(0), 150.0, 200.0));
        tx.commit();
        
        am.save("numberAlreadyExists", new Date(0), 150.0, 200.0);
    }
}
