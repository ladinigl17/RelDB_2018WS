package company.simplest.domain;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

import static org.junit.Assert.*;


@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class ManyToManyTest {

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "ue6_manytomany";

    static final String group1_name = "Group A";
    static final int group1_id = 1;
    static final String group2_name = "Group B";
    static final int group2_id = 2;


    static final int id = 1011;
    static final String name = "John";
    static final String eyecolor = "blue";
    static final int age = 45000;
    static final int height = 1000;


    static GroupManyToMany group1;
    static GroupManyToMany group2;
    static PersonManyToMany john;
    static PersonManyToMany jim;



    private static void reset() {
        transaction.begin();
        manager.createNativeQuery(
                "DELETE FROM ue6_manytomany.person").executeUpdate();
        manager.createNativeQuery(
                "ALTER SEQUENCE person_sequence RESTART").executeUpdate();
        manager.createNativeQuery(
                "DELETE FROM ue6_manytomany.group").executeUpdate();
        transaction.commit();


    }


    public static List<GroupManyToMany> findAllGroups() {
        TypedQuery<GroupManyToMany> query = manager.createQuery(
                "SELECT g FROM GroupManyToMany g"
                , GroupManyToMany.class);

        return query.getResultList();
    }

    public static List<PersonManyToMany> findAllPersons() {
        TypedQuery<PersonManyToMany> query = manager.createQuery(
                "SELECT p FROM PersonManyToMany p"
                , PersonManyToMany.class);

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
        john = new PersonManyToMany(name, eyecolor, height, age);
        jim = new PersonManyToMany(name, eyecolor, height, age);
        group1 = new GroupManyToMany(group1_id, group1_name);
        group2 = new GroupManyToMany(group2_id, group2_name);
        assertNotNull(john);
        assertNotNull(jim);
        assertNotNull(group1);
        assertNotNull(group1);
        manager.persist(john);
        manager.persist(jim);
        manager.persist(group1);
        manager.persist(group2);
        transaction.commit();

        System.out.println("Created and Persisted");

    }

    @Test
    public void join(){
        transaction.begin();
        john.addGroup(group1);
        john.addGroup(group2);
        jim.addGroup(group1);
        transaction.commit();
    }

    @Test(expected = AssertionError.class) //expected Exception because of doubleAss() Test
    public void verify() {
        john = manager.find(PersonManyToMany.class, id);
        assertNotNull(john);
        jim = manager.find(PersonManyToMany.class, id);
        assertNotNull(jim);
        List<PersonManyToMany> persons = findAllPersons();
        assertTrue(john.getGroups().contains(group1));
        assertTrue(john.getGroups().contains(group2));
        assertTrue(jim.getGroups().contains(group1));
        assertFalse(jim.getGroups().contains(group2));

    }


}
