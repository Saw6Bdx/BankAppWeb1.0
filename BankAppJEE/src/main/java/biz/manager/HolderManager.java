package biz.manager;

import biz.exception.NoHolderAvailableException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Holder;

@Stateless
public class HolderManager
{
  @PersistenceContext(unitName="BankAppPU")
  private EntityManager em;
  private List<Holder> holderList = new ArrayList();
  
  @Lock(LockType.READ)
  public List<Holder> displayHolder()
    throws NoHolderAvailableException
  {
    try
    {
      TypedQuery<Holder> qHolders = this.em.createNamedQuery("Holder.findAll", Holder.class);
      this.holderList = qHolders.getResultList();
      
      return this.holderList;
    }
    catch (NoResultException e)
    {
      throw new NoHolderAvailableException();
    }
  }
}
