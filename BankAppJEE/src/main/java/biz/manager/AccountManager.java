package biz.manager;

import biz.exception.AccountAlreadyExistingException;
import biz.exception.NoAccountAvailableException;
import java.util.ArrayList;
import java.util.Date;
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
public class AccountManager
{
  @PersistenceContext(unitName="BankAppPU")
  private EntityManager em;
  private List<Account> accountList = new ArrayList();
  
  @Lock(LockType.WRITE)
  public Account save(String number, Date creationDate, double firstBalance, double overdraft)
    throws AccountAlreadyExistingException
  {
    for (Account account : this.accountList) {
      if (account.getNumber().equals(number)) {
        throw new AccountAlreadyExistingException();
      }
    }
    Account newAccount = new Account(null, number, creationDate, firstBalance, overdraft);
    this.em.persist(newAccount);
    return newAccount;
  }
  
  @Lock(LockType.READ)
  public List<Account> displayAccount(int Id)
    throws NoAccountAvailableException
  {
    try
    {
      TypedQuery<Account> qAccounts = this.em.createQuery("SELECT a FROM Account a JOIN a.holderCollection h WHERE h.id =:holder", Account.class);
      qAccounts.setParameter("holder", Integer.valueOf(Id));
      this.accountList = qAccounts.getResultList();
      
      return this.accountList;
    }
    catch (NoResultException e)
    {
      throw new NoAccountAvailableException();
    }
  }
}
