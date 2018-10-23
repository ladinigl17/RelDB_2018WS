/*
 * project    company
 * subproject simplest
 */

package company.simplest.domain;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class PersonMissmatchTest {

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "ue2";

    static final int id = 162 ;
    static final String name = "John";
    static final String eyecolor = "blue";
    static final int  age = 45000;
    static final int height = 1000;

    @BeforeClass
    public static void setup() {
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
//        AssertNotNull(factory);
        manager = factory.createEntityManager();
//        assertNotNull(manager);

        transaction = manager.getTransaction();
    }

    @AfterClass
    public static void teardown() {
        if (manager == null)
            return;

        manager.close();
        factory.close();
    }


    @Test
    public void create() {
        transaction.begin();
        PersonMissmatch john = new PersonMissmatch(id, name, eyecolor, height, age);
//        assertNotNull(john);
        manager.persist(john);
        transaction.commit();

        System.out.println("Created and Persisted " + john);

    }

    @Test
    public void modify() {
        PersonMissmatch john = manager.find(PersonMissmatch.class, id);
        assertNotNull(john);
        System.out.println("Found " + john);

        transaction.begin();
        john.raiseHeight(height);
        transaction.commit();

        //#if STRICT
        //start from scratch - this ensures that john is fetched from the DB :
        //teardown ();
        //setup    ();
        //#endif

        john = manager.find(PersonMissmatch.class, id);

        assertEquals(height + height, (int) john.getHeight());
        System.out.println("Updated " + john);
    }

    @Test
    public void remove() {
        PersonMissmatch john = manager.find(PersonMissmatch.class, id);
        assertNotNull(john);

        transaction.begin();
        manager.remove(john);
        transaction.commit();

        john = manager.find(PersonMissmatch.class, id);
        assertNull(john);

        System.out.println("Removed " + id);
    }
}
