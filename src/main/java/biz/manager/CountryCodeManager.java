package biz.manager;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import model.CountryCode;
import biz.exception.CountryCodeAlreadyExistingException;
import biz.exception.CountryCodeDoesNotExistException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class CountryCodeManager {

    @PersistenceContext(unitName = "BankAppPU")
    private EntityManager entityManager;

    private List<CountryCode> countrycodes = new ArrayList<>();

    @Lock(LockType.WRITE)
    public CountryCode save(String code) throws CountryCodeAlreadyExistingException {
        
        setCountryCodeListFromDB();

        for (CountryCode countrycode : countrycodes) {
            if (countrycode.getCode().equals(code)) {
                throw new CountryCodeAlreadyExistingException();
            }
        }

        CountryCode newCountryCode = new CountryCode(null, code);
        this.entityManager.persist(newCountryCode);
        return newCountryCode;
    }

    @Lock(LockType.READ)
    public CountryCode getByCode(String code) throws CountryCodeDoesNotExistException {
        try {
            return this.entityManager.createQuery("SELECT c FROM CountryCode c WHERE c.code = :code", CountryCode.class)
                    .setParameter("code", code).getSingleResult();
        } catch (NoResultException e) {
            throw new CountryCodeDoesNotExistException();
        }
    }
    
    @Lock(LockType.READ) 
    private void setCountryCodeListFromDB() {
        TypedQuery<CountryCode> qCountryCode = this.entityManager.createNamedQuery("CountryCode.findAll", CountryCode.class);
        this.countrycodes = qCountryCode.getResultList();
    }
}
