package biz.manager;

import biz.exception.CountryCodeAlreadyExistingException;
import biz.exception.CountryCodeDoesNotExistException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.CountryCode;

@Stateless
public class CountryCodeManager
{
  @PersistenceContext(unitName="BankAppPU")
  private EntityManager entityManager;
  private List<CountryCode> countryCodes = new ArrayList();
  
  @Lock(LockType.WRITE)
  public CountryCode save(String code)
    throws CountryCodeAlreadyExistingException
  {
    for (CountryCode countrycode : this.countryCodes) {
      if (countrycode.getCode().equals(code)) {
        throw new CountryCodeAlreadyExistingException();
      }
    }
    CountryCode newCountryCode = new CountryCode(null, code);
    this.entityManager.persist(newCountryCode);
    return newCountryCode;
  }
  
  @Lock(LockType.READ)
  public CountryCode getByCode(String code)
    throws CountryCodeDoesNotExistException
  {
    try
    {
      return 
        (CountryCode)this.entityManager.createQuery("SELECT c FROM CountryCode c WHERE c.code = :code", CountryCode.class).setParameter("code", code).getSingleResult();
    }
    catch (NoResultException e)
    {
      throw new CountryCodeDoesNotExistException();
    }
  }
}
