package project.vehiculeTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.station.Station;
import project.vehicule.Vehicule;

/** class for testing Vehicule */
public abstract class AbstractVehiculeTest {

    /** create a Vehicule */
    protected Vehicule vehicule;

    /** create a Vehicule */
    public abstract Vehicule createVehicule();

    /** class StationMock */
    public class StationMock extends Station {
        public StationMock(int id, int capacity) {
            super(id, capacity);
        }
    }

    /** initialize the Vehicule */
    @BeforeEach
    public void initVehicule() {
        this.vehicule = this.createVehicule();
    }

    /** test the getId method */
    @Test
    public void testGetId() {
        assertEquals(1, this.vehicule.getId());
    }

    /** test the getWeight method */
    @Test
    public void testGetWeight() {
        assertEquals(1, this.vehicule.getWeight());
    }

    /** test the getusage method */
    @Test
    public void testGetUsage() {
        assertEquals(0, this.vehicule.getUsage());
    }

    /** test the setusage method */
    @Test
    public void testSetUsage() {
        this.vehicule.setusage(1);
        assertEquals(1, this.vehicule.getUsage());
    }

    /** test the isOutService method */
    @Test
    public void testIsOutService() {
        assertFalse(this.vehicule.isOutService());
    }

    /** test the setOutService method */
    @Test
    public void testSetOutService() {
        this.vehicule.setoutService(true);
        assertTrue(this.vehicule.isOutService());
    }

    /** test the getStation method */
    @Test
    public void testGetStation() {
        StationMock station = new StationMock(1, 1);
        this.vehicule.setStation(station);
        assertEquals(station, this.vehicule.getStation());
    }

}
