package project.stationTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.controlCenter.ControlCenter;
import project.station.Station;
import project.station.Exception.StationEmptyException;
import project.station.Exception.StationFullException;
import project.vehicule.Vehicule;
import project.vehicule.bike.ClassicBike;

import java.util.List;


/** class for testing Station */
public class StationTest {

    /** MockVehicle class */
    public class MockVehicle extends ClassicBike {
        public MockVehicle(int id, int weight) {
            super(id, weight);
        }
    }

    /** MockControlCenter class */
    public class MockControlCenter extends ControlCenter {

        /** updateCount */
        private int updateCount;
        
        /** constructor */
        public MockControlCenter() {
            super();
            this.updateCount = 0;
        }

        /** update method but only increment the updateCount
         * @param station
         * @param vehicule
         * @param isArrival
         */
        @Override
        public void update(Station station, Vehicule vehicule, boolean isArrival) {
            this.updateCount++;
        }
    }

    /** Station */
    private Station station;
    
    /** MockVehicle */
    private MockVehicle vehicule1;
    private MockVehicle vehicule2;

    /** MockControlCenter */
    private MockControlCenter controlCenter;
    

    /** initialize the Station */
    @BeforeEach
    public void setUp() {
        this.station = new Station(1, 2);  // station with capacity 2
        this.vehicule1 = new MockVehicle(1, 100);
        this.vehicule2 = new MockVehicle(2, 200);
        this.controlCenter = new MockControlCenter();
    }

    /** test the getId method */
    @Test
    public void testGetId() {
        assertEquals(1, station.getId());
    }

    /** test the getCapacity method */
    @Test
    public void testGetCapacity() {
        assertEquals(2, station.getCapacity());
    }

    /** test isEmpty method when the station is empty */
    @Test
    public void testIsEmpty_WhenStationIsEmpty() {
        assertTrue(station.isEmpty());
    }

    /** test isEmpty method when the station is not empty */
    @Test
    public void testIsEmpty_WhenStationIsNotEmpty() throws StationFullException {
        assertTrue(station.isEmpty());
        station.takeVehicule(vehicule1);
        assertFalse(station.isEmpty());
    }

    /** test isFull method when the station is full */
    @Test
    public void testIsFull_WhenStationIsFull() throws StationFullException {
        assertTrue(station.isEmpty());
        station.takeVehicule(vehicule1);
        station.takeVehicule(vehicule2);
        assertTrue(station.isFull());
    }

    /** test isFull method when the station is not full */
    @Test
    public void testIsFull_WhenStationIsNotFull() throws StationFullException {
        assertTrue(station.isEmpty());
        station.takeVehicule(vehicule1);
        assertFalse(station.isFull());
    }

    /** test giveVehicule method when the station is empty */
    @Test
    public void testGiveVehicule_WhenStationIsEmpty() {
        assertTrue(station.isEmpty());
        assertThrows(StationEmptyException.class, () -> station.giveVehicule());
    }

    /** test giveVehicule method when the station contains vehicles */
    @Test
    public void testGiveVehicule_WhenStationContainsVehicles() throws StationFullException, StationEmptyException {
        assertTrue(station.isEmpty());
        station.takeVehicule(vehicule1);
        station.takeVehicule(vehicule2);
        Vehicule removedVehicule = station.giveVehicule();
        assertEquals(vehicule2, removedVehicule);  // the last added vehicule should be removed
    }

    /** test takeVehicule method when the station is full */
    @Test
    public void testTakeVehicule_WhenStationIsFull() throws StationFullException {
        assertTrue(station.isEmpty());
        station.takeVehicule(vehicule1);
        station.takeVehicule(vehicule2);
        assertThrows(StationFullException.class, () -> station.takeVehicule(new MockVehicle(3, 300)));
    }

    /** test takeVehicule method when the station is not full */
    @Test
    public void testTakeVehicule_WhenStationIsNotFull() throws StationFullException {
        assertTrue(station.isEmpty());
        station.takeVehicule(vehicule1);
        List<Vehicule> vehicles = station.getVehicles();
        assertTrue(vehicles.contains(vehicule1));  // the vehicule should be added
    }

    /** test getVehicles method return a copy */
    @Test
    public void testGetVehicles_ReturnsCopy() throws StationFullException {
        station.takeVehicule(vehicule1);
        List<Vehicule> vehicles = station.getVehicles();
        assertEquals(1, vehicles.size());
        vehicles.add(new ClassicBike(3, 300));  // modify the returned list
        assertEquals(1, station.getVehicles().size());  // the original list should not be modified
    }

    /** test notifyObserver method when we add a vehicle */
    @Test
    public void testNotifyObserver_WhenAddingVehicle() throws StationFullException {
        this.controlCenter.addStation(station);
        this.station.setObserver(controlCenter);  // register the observer (ControlCenter)
        assertEquals(0, controlCenter.updateCount);

        station.takeVehicule(vehicule1);
        assertEquals(1, controlCenter.updateCount); // check if the observer is notified with the updateCount
    }

    /** test notifyObserver method when we remove a vehicle */
    @Test
    public void testNotifyObserver_WhenRemovingVehicle() throws StationFullException, StationEmptyException {
        this.controlCenter.addStation(station);  
        this.station.setObserver(controlCenter);  // register the observer (ControlCenter)
        assertEquals(0, controlCenter.updateCount);

        station.takeVehicule(vehicule1);  
        assertEquals(1, controlCenter.updateCount); // check if the observer is notified with the updateCount
        station.giveVehicule();  
        assertEquals(2, controlCenter.updateCount); // check if the observer is notified with the updateCount
    }

    /** test setObserver method */
    @Test
    public void testSetObserver() {
        station.setObserver(controlCenter);
        assertEquals(controlCenter, station.getObserver());
    }


}
