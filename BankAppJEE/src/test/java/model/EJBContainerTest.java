package model;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class EJBContainerTest {

	private static EJBContainer container;
	@Resource
	protected UserTransaction tx;
	@PersistenceContext(unitName = "accountPersistenceUnit")
	protected EntityManager em;

	@BeforeClass
	public static void start() throws Exception {
	    container = EJBContainer.createEJBContainer();
	}

	@AfterClass
	public static void shutdown() {
	    if (container != null) {
	        container.close();
	    }
	}

	public EJBContainerTest() {
		super();
	}

	@Before
	public void inject() throws Exception {
	    container.getContext().bind("inject", this);
	    truncateSchema();
	}

	private void truncateSchema() throws Exception {
		tx.begin();
		em.createNativeQuery("TRUNCATE SCHEMA PUBLIC AND COMMIT NO CHECK").executeUpdate();
		tx.commit();
	}

	@After
	public void reset() throws Exception {
	    container.getContext().unbind("inject");
	}

}