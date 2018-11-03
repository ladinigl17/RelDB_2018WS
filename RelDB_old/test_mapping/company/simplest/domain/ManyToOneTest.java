package company.simplest.domain;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

import static org.junit.Assert.*;


@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class ManyToOneTest {

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "ue5_manytoone";

    static final String address_name = "Herrengasse 5000";
    static final int address_id = 1;

    static final int id = 1011;
    static final String name = "John";
    static final String eyecolor = "blue";
    static final int age = 45000;
    static final int height = 1000;


    static AddressManyToOne address;
    static PersonManyToOne john = new PersonManyToOne(name, eyecolor, height, age);
    static PersonManyToOne jim = new PersonManyToOne(name, eyecolor, height, age);



    private static void reset() {
        transaction.begin();
        manager.createNativeQuery(
                "DELETE FROM ue5_manytoone.person").executeUpdate();
        manager.createNativeQuery(
                "ALTER SEQUENCE person_sequence RESTART").executeUpdate();
        manager.createNativeQuery(
                "DELETE FROM ue5_manytoone.address").executeUpdate();
        transaction.commit();


    }


    public static List<AddressManyToOne> findAllAddresses() {
        TypedQuery<AddressManyToOne> query = manager.createQuery(
                "SELECT a FROM AddressManyToOne a"
                , AddressManyToOne.class);

        return query.getResultList();
    }

    public static List<PersonManyToOne> findAllPersons() {
        TypedQuery<PersonManyToOne> query = manager.createQuery(
                "SELECT p FROM PersonManyToOne p"
                , PersonManyToOne.class);

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
        john = new PersonManyToOne(name, eyecolor, height, age);
        jim = new PersonManyToOne(name, eyecolor, height, age);
        address = new AddressManyToOne(address_id, address_name);
        assertNotNull(john);
        assertNotNull(jim);
        assertNotNull(address);
        manager.persist(john);
        manager.persist(jim);
        manager.persist(address);
        transaction.commit();

        System.out.println("Created and Persisted");

    }

    @Test
    public void join(){
        transaction.begin();
        john.setAddress(address);
        jim.setAddress(address);
        transaction.commit();
    }

    @Test(expected = AssertionError.class) //expected Exception because of doubleAss() Test
    public void verify() {
        john = manager.find(PersonManyToOne.class, id);
        assertNotNull(john);
        jim = manager.find(PersonManyToOne.class, id);
        assertNotNull(jim);
        List<PersonManyToOne> persons = findAllPersons();
        assertEquals(2, persons.size());
        assertEquals(john, persons.get(0));
        assertEquals(jim, persons.get(1));
        assertEquals(address, persons.get(0).getAddress());
        assertEquals(address, persons.get(1).getAddress());

        address = manager.find(AddressManyToOne.class, address_id);
        assertNotNull(address);
        List<AddressManyToOne> addresses = findAllAddresses();
        assertEquals(1, addresses.size());
        assertEquals(address, addresses.get(0));


    }
//
//    @Test
//    public void doubleAss(){
//        transaction.begin();
//        building2 = new Building(building2_id, building2_btype);
//
//        building2.setAddress(address);
//        try {
//            transaction.commit();
//            fail();
//        } catch (Exception exc) {
//
//        }
//    }

}
