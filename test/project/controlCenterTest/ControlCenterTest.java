package project.controlCenterTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.controlCenter.ControlCenter;
import project.station.Station;
import project.station.Exception.StationEmptyException;
import project.station.Exception.StationFullException;
import project.strategy.RandomStrategy;
import project.strategy.RoundRobin;
import project.vehicule.bike.ClassicBike;

import java.util.ArrayList;
import java.util.List;

/** Class of ControlCenterTest */
public class ControlCenterTest {

    /** Class of MockVehicule */
    public class MockVehicule extends ClassicBike {
        public MockVehicule(int id, int weight) {
            super(id, weight);
        }
    }

    /** Class of MockStation */
    public class MockStation extends Station {
        public MockStation(int id, int capacity) {
            super(id, capacity);
        }
    }

    /** Instance of ControlCenter */
    private ControlCenter ControlCenter;

    /** Instance of MockStation */
    private MockStation station1;
    private MockStation station2;

    /** Instance of MockVehicule */
    private MockVehicule vehicule1;
    private MockVehicule vehicule2;
    private MockVehicule vehicule3;
    private MockVehicule vehicule4;
    private MockVehicule vehicule5;

    /** Method to set up the test */
    @BeforeEach
    public void setUp() {
        ControlCenter = project.controlCenter.ControlCenter.getInstance();

        station1 = new MockStation(1, 4);
        station2 = new MockStation(2, 4);

        vehicule1 = new MockVehicule(1, 1);
        vehicule2 = new MockVehicule(2, 2);
        vehicule3 = new MockVehicule(3, 3);
        vehicule4 = new MockVehicule(4, 4);
        vehicule5 = new MockVehicule(5, 5);

        ControlCenter.clearStations(); // Clear the stations list of the ControlCenter
        ControlCenter.addStation(station1);
        ControlCenter.addStation(station2);
        
    }

    /** Test for the ControlCenter class */

    /** Test for the getInstance method */
    @Test
    public void testGetInstance() {
        assertEquals(ControlCenter, project.controlCenter.ControlCenter.getInstance());
    }

    /** Test for GetStations method */
    @Test
    public void testGetStations() {
        assertEquals(ControlCenter.getStations().size(), 2);
    }

    /** Test for setStations method */
    @Test
    public void testSetStations() {
        MockStation station3 = new MockStation(3, 4);
        MockStation station4 = new MockStation(4, 4);
        List<Station> stations = new ArrayList<>(); // Create a new list of stations

        stations.add(station3);
        stations.add(station4);

        assertEquals(ControlCenter.getStations().size(), 2);

        ControlCenter.setStations(stations);

        assertEquals(ControlCenter.getStations().size(), 2);
        assertTrue(ControlCenter.getStations().contains(station3)); // Check if the list contains the new stations
        assertTrue(ControlCenter.getStations().contains(station4));

    }

    /** Test for addStation method */
    @Test
    public void testAddStation() {
        MockStation station3 = new MockStation(3, 4);
        ControlCenter.addStation(station3);
        assertEquals(ControlCenter.getStations().size(), 3);
    }

    /** Test for removeStation method */
    @Test
    public void testRemoveStation() {
        ControlCenter.removeStation(station1);
        assertEquals(ControlCenter.getStations().size(), 1);
    }

    /** Test for setRedStrategy method */
    @Test
    public void testSetRedStrategy() {
        ControlCenter.setRedStrategy(RandomStrategy.getInstance());
        assertEquals(ControlCenter.getRedStrategy(), RandomStrategy.getInstance());
    }

    /** Test for getRedStrategy method */
    @Test
    public void testGetRedStrategy() {
        assertEquals(ControlCenter.getRedStrategy(), RoundRobin.getInstance());
    }

    /** Test for redistribute method */
    @Test
    public void testRedistribute() throws StationFullException, StationEmptyException{
        station1.takeVehicule(vehicule1);
        station1.takeVehicule(vehicule2);
        station2.takeVehicule(vehicule3);
        station2.takeVehicule(vehicule4);
        station2.takeVehicule(vehicule5);

        assertEquals(station1.getVehicles().size(), 2);
        assertEquals(station2.getVehicles().size(), 3);

        ControlCenter.reallocate();
        assertEquals(station1.getVehicles().size(), 3);
        assertEquals(station2.getVehicles().size(), 2);
    }

    /** Test for redistribute method with empty stations */
    @Test
    public void testRedistributeEmpty() throws StationFullException, StationEmptyException {
        ControlCenter.reallocate();
        assertEquals(station1.getVehicles().size(), 0);
        assertEquals(station2.getVehicles().size(), 0);
    }

}
