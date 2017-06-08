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

import biz.exception.LoginAlreadyExistingException;
import biz.exception.NoHolderAvailableException;
import biz.exception.PasswordsNotIdenticalException;
import model.Address;
import model.Holder;
import model.Postcode;

@Stateless
public class HolderMgr
{
  @PersistenceContext(unitName="BankAppPU")
  private EntityManager em;
  private List<Holder> holdersList = new ArrayList<Holder>();
  
  @Lock(LockType.WRITE)
  public void createUser(Holder holder, Address address, Postcode postcode, String pwdConfirmation) 
          throws LoginAlreadyExistingException, PasswordsNotIdenticalException {

	  getHolderFromDB();

      // Check if the login is already used
      for (Holder hldr : holdersList) {
          if (hldr.getLogin().equals(holder.getLogin())) {
              throw new LoginAlreadyExistingException();
          }
      }
      
      // Check if the password and its confirmation are identical
      if (!holder.getPassword().equals(pwdConfirmation)) {
          throw new PasswordsNotIdenticalException();
      }
      // Check if the postcode and the city already exist in the database
      this.em.persist(holder);
      this.em.persist(address);
      this.em.persist(postcode);

  }
  
  @Lock(LockType.READ)
  public List<Holder> displayHolder()
    throws NoHolderAvailableException
  {
    try
    {
      TypedQuery<Holder> qHolders = this.em.createNamedQuery("Holder.findAll", Holder.class);
      this.holdersList = qHolders.getResultList();
      
      return this.holdersList;
    }
    catch (NoResultException e)
    {
      throw new NoHolderAvailableException();
    }
  }
  
  @Lock(LockType.READ)
  private void getHolderFromDB() {
      TypedQuery<Holder> qHolder = this.em.createNamedQuery("Holder.findAll", Holder.class);
      this.holdersList = qHolder.getResultList();
  }
}
