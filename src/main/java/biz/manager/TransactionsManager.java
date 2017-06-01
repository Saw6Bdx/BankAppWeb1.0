/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manager;

import biz.exception.NoTransactionsAvailableException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Transactions;

/**
 *
 * @author Guest
 */
@Stateless
public class TransactionsManager {

    @PersistenceContext(unitName = "BankAppPU")
    private EntityManager em;
    

    private List<Transactions> transactionsList = new ArrayList<>();

    @Lock(LockType.READ)
    public List<Transactions> displayTransactions() throws NoTransactionsAvailableException {

        try {
            TypedQuery<Transactions> qTransactions = this.em.createNamedQuery("Transactions.findAll", Transactions.class);
            this.transactionsList = qTransactions.getResultList();

            return this.transactionsList;
        } catch (NoResultException e) {
            throw new NoTransactionsAvailableException();
        }

    }

    public void delete(String parameter) {

        System.out.println("delete(TransactionsManager) :"+parameter);
        TypedQuery<Transactions> qTransactions = this.em.createQuery("SELECT t FROM Transactions t WHERE t.id=:ptrans", Transactions.class);
        qTransactions.setParameter("ptrans", Integer.parseInt(parameter));
        Transactions transaction = qTransactions.getResultList().get(0);
        this.em.remove(transaction);
        
    }
}
