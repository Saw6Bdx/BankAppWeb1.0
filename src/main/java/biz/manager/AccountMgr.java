package biz.manager;

import biz.exception.AccountAlreadyExistingException;
import biz.exception.NoAccountAvailableException;
import biz.exception.NoAgencyAvailableException;
import biz.exception.NoCountryCodeAvailableException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Account;
import model.AccountType;
import model.Address;
import model.Agency;
import model.Bank;
import model.CountryCode;
import model.Postcode;

@Stateless
public class AccountMgr {

    @PersistenceContext(unitName = "BankAppPU")
    private EntityManager em;
    private List<Account> accountList = new ArrayList<Account>();
    private List<AccountType> accountTypeList = new ArrayList<AccountType>();
    private List<CountryCode> countryCodeList = new ArrayList<CountryCode>();
    private List<Agency> agencyList = new ArrayList<Agency>();
    private List<Postcode> postcodesList = new ArrayList<Postcode>();
    private List<Bank> bankcodesList = new ArrayList<Bank>();

    public Account save(String number, Date creationDate, double firstBalance, double overdraft)
            throws AccountAlreadyExistingException {
        for (Account account : this.accountList) {
            if (account.getNumber().equals(number)) {
                throw new AccountAlreadyExistingException();
            }
        }
        Account newAccount = new Account(null, number, creationDate, firstBalance, overdraft);
        this.em.persist(newAccount);
        return newAccount;
    }

    public void createAccount(Account account) {//, Agency agency, Bank bank, AccountManager accountManager){

        this.em.persist(account);
        /*this.em.persist(agency);
	  this.em.persist(bank);
	  this.em.persist(accountManager);*/

    }

    public List<Account> displayAccount() throws NoAccountAvailableException {
        try {
            TypedQuery<Account> qAccounts = this.em.createNamedQuery("Account.findAll", Account.class);
            this.accountList = qAccounts.getResultList();

            return this.accountList;
        } catch (NoResultException e) {
            throw new NoAccountAvailableException();
        }
    }

    public List<Account> displayAccount(int Id)
            throws NoAccountAvailableException {
        try {
            TypedQuery<Account> qAccounts = this.em.createQuery("SELECT a FROM Account a JOIN a.holderCollection h WHERE h.id =:holder", Account.class);
            qAccounts.setParameter("holder", Id);
            this.accountList = qAccounts.getResultList();

            return this.accountList;
        } catch (NoResultException e) {
            throw new NoAccountAvailableException();
        }
    }

    public List<AccountType> displayAccountType() throws NoAccountAvailableException {
        try {
            TypedQuery<AccountType> qAccountType = this.em.createNamedQuery("AccountType.findAll", AccountType.class);
            this.accountTypeList = qAccountType.getResultList();

            return this.accountTypeList;
        } catch (NoResultException e) {
            throw new NoAccountAvailableException();
        }
    }

    public List<CountryCode> displayCountryCode()
            throws NoCountryCodeAvailableException {
        try {
            TypedQuery<CountryCode> qCountryCode = this.em.createNamedQuery("CountryCode.findAll", CountryCode.class);
            this.countryCodeList = qCountryCode.getResultList();

            return this.countryCodeList;
        } catch (NoResultException e) {
            throw new NoCountryCodeAvailableException();
        }
    }

    public List<Agency> displayAgency()
            throws NoAgencyAvailableException {
        try {
            TypedQuery<Agency> qAgency = this.em.createNamedQuery("Agency.findAll", Agency.class);
            this.agencyList = qAgency.getResultList();

            return this.agencyList;
        } catch (NoResultException e) {
            throw new NoAgencyAvailableException();
        }
    }

    public List<Account> getBalanceAccount(int Id)
            throws NoAccountAvailableException {
        try {
            TypedQuery<Account> qAccounts = this.em.createQuery("SELECT a FROM Account a JOIN a.holderCollection h WHERE h.id =:holder", Account.class);
            qAccounts.setParameter("holder", Id);
            this.accountList = qAccounts.getResultList();

            return this.accountList;
        } catch (NoResultException e) {
            throw new NoAccountAvailableException();
        }
    }

    public double getOverdraft(int Id) {
        TypedQuery<Account> qAccounts = this.em.createQuery("SELECT a FROM Account a WHERE a.id =:pid", Account.class);
        qAccounts.setParameter("pid", Id);
        return qAccounts.getResultList().get(0).getOverdraft();
    }

    public double getFirstBalance(int Id) {
        TypedQuery<Account> qAccounts = this.em.createQuery("SELECT a FROM Account a WHERE a.id =:pid", Account.class);
        qAccounts.setParameter("pid", Id);
        return qAccounts.getResultList().get(0).getFirstBalance();
    }

    public double[] sumTransactionsByAccount(List<Account> accountList) {

        double[] sum = new double[accountList.size()];

        for (int i = 0; i < accountList.size(); i++) {
            sum[i] = 0.0;
        }

        for (int i = 0; i < accountList.size(); i++) {

            TypedQuery<Double> qTransactions = this.em.createQuery("SELECT SUM(t.amount) FROM Transactions t WHERE t.idAccount.id =:acc", Double.class);
            List<Double> transactionsList = qTransactions.setParameter("acc", accountList.get(i).getId()).getResultList();

            if (transactionsList.get(0) != null) {
                sum[i] = transactionsList.get(0);
            }

        }
        return sum;
    }

    public void delete(int Id) {
        Account account = getAccount(Id);
        this.em.remove(account);
    }

    public Account getAccount(int Id) {
        TypedQuery<Account> qAccount = this.em.createNamedQuery("Account.findById", Account.class);
        qAccount.setParameter("id", Id);
        return qAccount.getResultList().get(0);
    }
    
    public Agency getAgency(int Id) {
        TypedQuery<Agency> qAgency = this.em.createNamedQuery("Agency.findById", Agency.class);
        qAgency.setParameter("id", Id);
        return qAgency.getResultList().get(0);
    }

    public void modify(Account account, CountryCode countryCode, Agency agency, Bank bank, Address address, Postcode postcode) {
        
        // Check if the postcode already exists
        getPostcodeFromDB();
        boolean flagPostcode = false;
        for (Postcode pc : this.postcodesList) {
            if (pc.getPostcode() == postcode.getPostcode()) {
                address.setIdPostcode(pc);
                flagPostcode = true;
            }
        }
        if (!flagPostcode) {
            this.em.persist(postcode);
        }
        
        // Check if the bank code already exists
        getBankFromDB();
        boolean flagBankcode = false;
        for (Bank bk : this.bankcodesList) {
            if (bk.getBankCode().equals(bank.getBankCode())) {
                agency.setIdBank(bk);
                flagBankcode = true;
            }
        }
        if (!flagBankcode) {
            this.em.persist(bank);
        }
        agency.setIdAddress(address);
        
        account.setIdCountryCode(countryCode);
        account.setIdAgency(agency);
        
        this.em.merge(account);
        
    }
    
    private void getPostcodeFromDB() {
        TypedQuery<Postcode> qPostcode = this.em.createNamedQuery("Postcode.findAll", Postcode.class);
        this.postcodesList = qPostcode.getResultList();
    }

    private void getBankFromDB() {
        TypedQuery<Bank> qBank = this.em.createNamedQuery("Bank.findAll", Bank.class);
        this.bankcodesList = qBank.getResultList();
    }

}
