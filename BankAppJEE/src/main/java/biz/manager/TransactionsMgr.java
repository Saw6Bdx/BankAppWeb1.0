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

import biz.exception.NoTransactionsAvailableException;
import model.TransactionType;
import model.Transactions;

@Stateless
public class TransactionsMgr
{
  @PersistenceContext(unitName="BankAppPU")
  private EntityManager em;
  private List<Transactions> transactionsList = new ArrayList<Transactions>();
  private List<TransactionType> transactionTypeList = new ArrayList<TransactionType>();
  
  @Lock(LockType.WRITE)
  public void createTransactions(Transactions transactions){
	  
	  this.em.persist(transactions);

  }
  
  @Lock(LockType.READ)
  public List<Transactions> displayTransactions(int Id)
    throws NoTransactionsAvailableException
  {
    try
    {
      TypedQuery<Transactions> qTransactions = this.em.createQuery("SELECT t FROM Transactions t JOIN t.idAccount a WHERE a.id =:account", Transactions.class);
      qTransactions.setParameter("account", Integer.valueOf(Id));
      this.transactionsList = qTransactions.getResultList();
      
      return this.transactionsList;
    }
    catch (NoResultException e)
    {
      throw new NoTransactionsAvailableException();
    }
  }
  
  @Lock(LockType.READ)
  public List<TransactionType> displayTransactionType()
    throws NoTransactionsAvailableException
  {
    try
    {
      TypedQuery<TransactionType> qTransactionType = this.em.createNamedQuery("TransactionType.findAll", TransactionType.class);
      this.transactionTypeList = qTransactionType.getResultList();
      
      return this.transactionTypeList;
    }
    catch (NoResultException e)
    {
      throw new NoTransactionsAvailableException();
    }
  }
}
