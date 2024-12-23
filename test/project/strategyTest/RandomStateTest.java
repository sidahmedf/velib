package project.strategyTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.station.Station;
import project.station.Exception.StationEmptyException;
import project.station.Exception.StationFullException;
import project.strategy.RandomStrategy;
import project.vehicule.bike.ClassicBike;

import java.util.ArrayList;
import java.util.List;

/** class for testing RandomState */
public class RandomStateTest {

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

    /** RandomStrategy */
    private RandomStrategy strategy = RandomStrategy.getInstance();

    /** mockStations */
    private mockStations station1;
    private mockStations station2;

    /** MockBike */
    private MockBike vehicule1;
    private MockBike vehicule2;
    private MockBike vehicule3;

    /** list of stations */
    private List<Station> listStations;

    /** total vehicles */
    private int totalVehicles = 3;

    /** initialize the stations and vehicles */
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
        assertEquals(strategy, RandomStrategy.getInstance());
    }

    /** test the redistribute method */
    @Test
    public void redistributeTest() throws StationFullException, StationEmptyException {
        station1.takeVehicule(vehicule1);
        station2.takeVehicule(vehicule2);
        station2.takeVehicule(vehicule3);
        strategy.redistribute(listStations);
        int count = 0;
        for (Station station : listStations) {
            count += station.getVehicles().size();
        }
        // the place of the vehicles is not important because it is random but we want to know if the number of vehicles is the same
        // so we compare the total number of vehicles before and after the redistribution
        assertEquals(totalVehicles, count); 
    }

    /** test the redistribute method when the station is empty */
    @Test
    public void redistributeEmptyStationTest() throws StationEmptyException, StationFullException {
        strategy.redistribute(listStations);
        int count = 0;
        for (Station station : listStations) {
            count += station.getVehicles().size();
        }
        assertEquals(0, count);
    }

    
}
