/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manager;

import biz.exception.NoTransactionsAvailableException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.TransactionType;
import model.Transactions;

/**
 *
 * @author Guest
 */
@Stateless
public class TransactionsMgr {

    @PersistenceContext(unitName = "BankAppPU")
    private EntityManager em;

    private List<Transactions> transactionsList = new ArrayList<Transactions>();
    private List<TransactionType> transactionTypeList = new ArrayList<TransactionType>();

    public void createTransactions(Transactions transactions) {

        this.em.persist(transactions);

    }

    public List<Transactions> displayTransactions(int Id) throws NoTransactionsAvailableException {

        try {
            TypedQuery<Transactions> qTransactions = this.em.createQuery("SELECT t FROM Transactions t JOIN t.idAccount a WHERE a.id =:account ORDER BY t.date DESC", Transactions.class);
            qTransactions.setParameter("account", Id);
            this.transactionsList = qTransactions.getResultList();

            return this.transactionsList;
        } catch (NoResultException e) {
            throw new NoTransactionsAvailableException();
        }

    }

    public List<TransactionType> displayTransactionType() throws NoTransactionsAvailableException {
        try {
            TypedQuery<TransactionType> qTransactionType = this.em.createNamedQuery("TransactionType.findAll", TransactionType.class);
            this.transactionTypeList = qTransactionType.getResultList();

            return this.transactionTypeList;
        } catch (NoResultException e) {
            throw new NoTransactionsAvailableException();
        }
    }

    public void delete(String parameter) {

        TypedQuery<Transactions> qTransactions = this.em.createQuery("SELECT t FROM Transactions t WHERE t.id=:ptrans", Transactions.class);
        qTransactions.setParameter("ptrans", Integer.parseInt(parameter));
        Transactions transaction = qTransactions.getResultList().get(0);
        this.em.remove(transaction);

    }

    public List<Transactions> transactionsOrderBy(int Id, String param, String order) throws NoTransactionsAvailableException {

        TypedQuery<Transactions> qTransactions;

        switch (param) {
            case "category":
                qTransactions = this.em.createQuery("SELECT t FROM Transactions t JOIN t.idAccount a WHERE a.id =:account ORDER BY t.idCategory.label " + order, Transactions.class);
                break;
            case "debit":
                qTransactions = this.em.createQuery("SELECT t FROM Transactions t JOIN t.idAccount a WHERE a.id =:account ORDER BY t.amount " + order, Transactions.class);
                break;
            case "credit":
                qTransactions = this.em.createQuery("SELECT t FROM Transactions t JOIN t.idAccount a WHERE a.id =:account ORDER BY t.amount " + order, Transactions.class);
                break;
            default: // date
                qTransactions = this.em.createQuery("SELECT t FROM Transactions t JOIN t.idAccount a WHERE a.id =:account ORDER BY t.date " + order, Transactions.class);
                break;
        }

        qTransactions.setParameter("account", Id);

        try {
            this.transactionsList = qTransactions.getResultList();
            return this.transactionsList;
        } catch (NoResultException e) {
            throw new NoTransactionsAvailableException();
        }

    }

    public void modify(String Id, Date date, String label, String amount, String idCategory) {
        
        TypedQuery<Transactions> qTransactions = this.em.createQuery("SELECT t FROM Transactions t WHERE t.id=:ptrans", Transactions.class);
        qTransactions.setParameter("ptrans", Integer.parseInt(Id));
        Transactions transaction = qTransactions.getResultList().get(0);
        
        Transactions trans = new Transactions(
                Integer.parseInt(Id), 
                label, 
                Double.parseDouble(amount), 
                date,
                transaction.getEndDate()
        );
        
        this.em.merge(trans);
        
    }

    public Date getEndDate(int Id) {
        TypedQuery<Transactions> qTransactions = this.em.createQuery("SELECT t FROM Transactions t WHERE t.id=:pid",Transactions.class);
        qTransactions.setParameter("pid",Id);
        return qTransactions.getResultList().get(0).getEndDate();
    }
    
}
