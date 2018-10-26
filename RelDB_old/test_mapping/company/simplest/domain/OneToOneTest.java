package company.simplest.domain;

import company.simplest.domain.Address;
import company.simplest.domain.Building;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;



@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class OneToOneTest {

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "ue_4_onetoone";

    static final String building1_btype = "A";
    static final int building1_id = 1;

    static final String building2_btype = "B";
    static final int building2_id = 2;

    static final String address_name = "Herrengasse 5000";
    static final int address_id = 1;

    static Building building1;
    static Building building2;
    static Address address;



    private static void reset() {
        transaction.begin();
        manager.createNativeQuery(
                "DELETE FROM ue_4_onetoone.building").executeUpdate();
        manager.createNativeQuery(
                "DELETE FROM ue_4_onetoone.address").executeUpdate();
        transaction.commit();
    }

    public static List<Building> findAllBuildings() {
        TypedQuery<Building> query = manager.createQuery(
                "SELECT b FROM Building b"
                , Building.class);

        return query.getResultList();
    }

    public static List<Address> findAllAddresses() {
        TypedQuery<Address> query = manager.createQuery(
                "SELECT a FROM Address a"
                , Address.class);

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
        building1 = new Building(building1_id, building1_btype);
        address = new Address(address_id, address_name);
        assertNotNull(building1);
        assertNotNull(address);
        manager.persist(building1);
        manager.persist(address);
        transaction.commit();

        System.out.println("Created and Persisted ");

    }

    @Test
    public void join(){
        transaction.begin();
        building1.setAddress(address);
        transaction.commit();
    }

    @Test
    public void verify() {
        building1 = manager.find(Building.class, building1_id);
        assertNotNull(building1);
        List<Building> buildings = findAllBuildings();
        assertEquals(1, buildings.size());
        assertEquals(building1, buildings.get(0));
        assertEquals(address, building1.getAddress());

//        address = manager.find(Address.class, address_id);
//        assertNotNull(address);
//        List<Address> addresses = findAllAddresses();
//        assertEquals(1, addresses.size());
//        assertEquals(address, addresses.get(0));
//        assertEquals(building1, address.getBuilding());

    }


//  GEHT NIX
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
