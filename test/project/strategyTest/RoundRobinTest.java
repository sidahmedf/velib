package project.strategyTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.station.Station;
import project.station.Exception.StationEmptyException;
import project.station.Exception.StationFullException;
import project.strategy.RoundRobin;
import project.vehicule.bike.ClassicBike;

import java.util.ArrayList;
import java.util.List;

/** class for testing RoundRobin */
public class RoundRobinTest {

    /** mockStations class */
    public class mockStations extends Station {
        public mockStations(int id, int x) {
            super(id, x);
        }
    }

    /** MockBike class */
    public class MockBike extends ClassicBike {
        public MockBike(int id, int weight, int seatHeight) {
            super(id, weight);
        }
    }

    /** RoundRobin */
    private RoundRobin strategy = RoundRobin.getInstance();

    /** mockStations */
    private mockStations station1;
    private mockStations station2;

    /** MockBike */
    private MockBike vehicule1;
    private MockBike vehicule2;
    private MockBike vehicule3;

    /** list of stations */
    private List<Station> listStations;

    /** set up the stations and vehicles */
    @BeforeEach
    public void setUp() {
        station1 = new mockStations(1, 4);
        station2 = new mockStations(2, 4);

        vehicule1 = new MockBike(1, 1, 1);
        vehicule2 = new MockBike(2, 2, 2);
        vehicule3 = new MockBike(3, 3, 3);

        listStations = new ArrayList<>();
        listStations.add(station1);
        listStations.add(station2);
    }

    /** test the getInstance method */
    @Test
    public void getInstanceTest() {
        assertEquals(strategy, RoundRobin.getInstance());
    }

    /** test the redistribute method */
    @Test
    public void redistributeTest() throws StationFullException, StationEmptyException {
        station1.takeVehicule(vehicule1);
        station2.takeVehicule(vehicule2);
        station2.takeVehicule(vehicule3);
        strategy.redistribute(listStations);
        // with round robin, the first station should have 2 vehicles and the second station should have 1 vehicle
        // because the first station has the least number of vehicles
        assertEquals(2, station1.getVehicles().size());
        assertEquals(1, station2.getVehicles().size());
    }

    /** test the redistribute method when the stations are empty */
    @Test
    public void redistributeEmptyStationTest() throws StationEmptyException, StationFullException {
        strategy.redistribute(listStations);
        assertEquals(0, station1.getVehicles().size());
        assertEquals(0, station2.getVehicles().size());
    }

    /** test the redistribute method when the stations are full */
    @Test
    public void redistributeFullStationTest() throws StationFullException, StationEmptyException {
        station1.takeVehicule(vehicule1);
        station1.takeVehicule(vehicule2);
        station1.takeVehicule(vehicule3);
        station1.takeVehicule(vehicule1);
        station2.takeVehicule(vehicule2);
        station2.takeVehicule(vehicule3);
        station2.takeVehicule(vehicule1);
        station2.takeVehicule(vehicule2);
        strategy.redistribute(listStations);
        assertEquals(4, station1.getVehicles().size());
        assertEquals(4, station2.getVehicles().size());
    }


}
