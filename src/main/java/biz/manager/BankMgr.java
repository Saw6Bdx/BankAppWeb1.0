package biz.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import biz.exception.NoBankAvailableException;
import model.AccountManager;
import model.Address;
import model.Agency;
import model.Bank;
import model.Postcode;

@Stateless
public class BankMgr {

    @PersistenceContext(unitName = "BankAppPU")
    private EntityManager em;
    private List<Bank> bankList = new ArrayList<Bank>();

    public void createBank(Agency agency, AccountManager accountManager, Address address, Postcode postcode) {

        this.em.persist(accountManager);
        this.em.persist(agency);
        this.em.persist(address);
        this.em.persist(postcode);
    }

    public List<Bank> displayBank() throws NoBankAvailableException {
        try {
            TypedQuery<Bank> qBank = this.em.createNamedQuery("Bank.findAll", Bank.class);
            this.bankList = qBank.getResultList();

            return this.bankList;
        } catch (NoResultException e) {
            throw new NoBankAvailableException();
        }
    }
}
