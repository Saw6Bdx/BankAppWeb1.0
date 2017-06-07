/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manager;

import javax.annotation.Resource;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author administrateur
 */
public class EjbContainerTest {
    
    private static EJBContainer container;
    
    @PersistenceContext(unitName="BankAppPU")
    protected EntityManager em;

    @Resource
    protected UserTransaction tx;

    @BeforeClass
    public static void start() throws Exception {
        // static car existe dans la classe et peut être appelé sans instance AccountManagerTest.start();
        container = EJBContainer.createEJBContainer();
    }

    @AfterClass
    public static void shutdown() {
        if (container != null) {
            container.close();
        }
    }
    
    @Before
    public void inject() throws Exception {
        container.getContext().bind("inject", this);
        truncateSchema();
    }
    
    protected void truncateSchema() throws Exception {
        tx.begin();
        em.createNativeQuery("TRUNCATE SCHEMA PUBLIC AND COMMIT NO CHECK").executeUpdate();
        tx.commit();
    }

    @After
    public void reset() throws Exception {
        container.getContext().unbind("inject");
    }
    
}
