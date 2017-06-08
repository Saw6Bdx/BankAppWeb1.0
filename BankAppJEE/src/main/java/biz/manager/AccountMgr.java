package biz.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import biz.exception.NoAccountAvailableException;
import biz.exception.NoAgencyAvailableException;
import biz.exception.NoCountryCodeAvailableException;
import model.Account;
import model.AccountType;
import model.Agency;
import model.CountryCode;

@Stateless
public class AccountMgr
{
  @PersistenceContext(unitName="BankAppPU")
  private EntityManager em;
  private List<Account> accountList = new ArrayList<Account>();
  private List<AccountType> accountTypeList = new ArrayList<AccountType>();
  private List<CountryCode> countryCodeList = new ArrayList<CountryCode>();
  private List<Agency> agencyList = new ArrayList<Agency>();
  
  @Lock(LockType.WRITE)
  public void createAccount(Account account){//, Agency agency, Bank bank, AccountManager accountManager){
	  
	  this.em.persist(account);
	  /*this.em.persist(agency);
	  this.em.persist(bank);
	  this.em.persist(accountManager);*/

  }
  
  @Lock(LockType.READ)
  public List<Account> displayAccount()
    throws NoAccountAvailableException
  {
    try
    {
      TypedQuery<Account> qAccounts = this.em.createNamedQuery("Account.findAll", Account.class);
      this.accountList = qAccounts.getResultList();
      
      return this.accountList;
    }
    catch (NoResultException e)
    {
      throw new NoAccountAvailableException();
    }
  }
  
  @Lock(LockType.READ)
  public List<Account> displayAccount(int Id)
    throws NoAccountAvailableException
  {
    try
    {
      TypedQuery<Account> qAccounts = this.em.createQuery("SELECT a FROM Account a JOIN a.holderCollection h WHERE h.id =:holder", Account.class);
      qAccounts.setParameter("holder", Id);
      this.accountList = qAccounts.getResultList();
      
      return this.accountList;
    }
    catch (NoResultException e)
    {
      throw new NoAccountAvailableException();
    }
  }
  
  @Lock(LockType.READ)
  public List<AccountType> displayAccountType()
    throws NoAccountAvailableException
  {
    try
    {
      TypedQuery<AccountType> qAccountType = this.em.createNamedQuery("AccountType.findAll", AccountType.class);
      this.accountTypeList = qAccountType.getResultList();
      
      return this.accountTypeList;
    }
    catch (NoResultException e)
    {
      throw new NoAccountAvailableException();
    }
  }
  
  @Lock(LockType.READ)
  public List<CountryCode> displayCountryCode()
    throws NoCountryCodeAvailableException
  {
    try
    {
      TypedQuery<CountryCode> qCountryCode = this.em.createNamedQuery("CountryCode.findAll", CountryCode.class);
      this.countryCodeList = qCountryCode.getResultList();
      
      return this.countryCodeList;
    }
    catch (NoResultException e)
    {
      throw new NoCountryCodeAvailableException();
    }
  }
  
  @Lock(LockType.READ)
  public List<Agency> displayAgency()
    throws NoAgencyAvailableException
  {
    try
    {
      TypedQuery<Agency> qAgency = this.em.createNamedQuery("Agency.findAll", Agency.class);
      this.agencyList = qAgency.getResultList();
      
      return this.agencyList;
    }
    catch (NoResultException e)
    {
      throw new NoAgencyAvailableException();
    }
  }
}
