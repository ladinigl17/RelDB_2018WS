/*
 * project    company
 * subproject simplest
 */

package company.simplest.domain;

import company.simplest.domain.PersonSequence.Color;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.*;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class PersonSequenceTest {

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "ue3";

    static final int id = 1011;
    static final String name = "John";
    static final String eyecolor = "blue";
    static final int age = 45000;
    static final int height = 1000;
    static final Color haircolor = Color.red;

    private static void reset() {
        transaction.begin();
        manager.createNativeQuery(
                "DELETE FROM ue3.person").executeUpdate();
        manager.createNativeQuery(
                "ALTER SEQUENCE person_sequence RESTART").executeUpdate();
        transaction.commit();
    }

    public static List<PersonSequence> findAll() {
        TypedQuery<PersonSequence> query = manager.createQuery(
                "SELECT p FROM PersonSequence p"
                , PersonSequence.class);

        return query.getResultList();
    }


    @BeforeClass
    public static void setup() {
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);


//        AssertNotNull(factory);
        manager = factory.createEntityManager();
//        assertNotNull(manager);

        transaction = manager.getTransaction();

        reset();
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
        PersonSequence john = new PersonSequence(name, eyecolor, height, age, haircolor);
//        assertNotNull(john);
        manager.persist(john);
        transaction.commit();

        System.out.println("Created and Persisted " + john);

    }

    @Test
    public void verify() {
        PersonSequence person = manager.find(PersonSequence.class, id);
        assertNotNull(person);
        List<PersonSequence> persons = findAll();
        assertEquals(1, persons.size());
        assertEquals(person, persons.get(0));
    }

}
