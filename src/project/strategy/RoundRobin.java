package project.strategy;

import java.util.ArrayList;
import java.util.List;

import project.station.Station;
import project.station.Exception.StationEmptyException;
import project.station.Exception.StationFullException;
import project.vehicule.Vehicule;

/** Singleton class of RoundRobin implementing RedistributionStrategy */
public class RoundRobin implements RedistributionStrategy {

    /** Private instance of RoundRobin */
    private static final RoundRobin INSTANCE = new RoundRobin();

    /** Private constructor to prevent external instantiation */
    private RoundRobin() {
        // Prevent instantiation
    }

    /** Public static method to get the instance of RoundRobin */
    public static RoundRobin getInstance() {
        return INSTANCE;
    }

    /** Method to redistribute the stations
    * @param stations the list of stations
    * @throws StationEmptyException 
    * @throws StationFullException 
    */
    @Override
    public void redistribute(List<Station> stations) throws StationEmptyException, StationFullException {
        List<Vehicule> vehicules = new ArrayList<>();

        // First collect all vehicles from all stations
        for (Station station : stations) {
            while (!station.isEmpty()) {
                Vehicule vehicule = station.giveVehicule();  // Remove vehicle from station
                vehicules.add(vehicule);
            }
        }

        // Redistribute the vehicles across stations using round-robin
        int stationIndex = 0;
        while (!vehicules.isEmpty()) {
            Station station = stations.get(stationIndex);
            if (!station.isFull() && !vehicules.isEmpty()) {
                // Only add the vehicle if the station is not full
                station.takeVehicule(vehicules.remove(0));  // Add vehicle to station
            }
            // Move to the next station in a round-robin manner
            stationIndex = (stationIndex + 1) % stations.size();
        }
    }
}
