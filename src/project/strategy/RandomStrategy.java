package project.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import project.station.Station;
import project.station.Exception.StationEmptyException;
import project.station.Exception.StationFullException;
import project.vehicule.Vehicule;

/** Singleton class of RandomStrategy implementing RedistributionStrategy */
public class RandomStrategy implements RedistributionStrategy {

    /** Private instance of RandomStrategy */
    private static final RandomStrategy INSTANCE = new RandomStrategy();

    /** Private constructor to prevent external instantiation */
    private RandomStrategy() {
        // Prevent instantiation
    }

    /** Public static method to get the instance of RandomStrategy */
    public static RandomStrategy getInstance() {
        return INSTANCE;
    }

    /** Method to redistribute the stations
    * @param stations the list of stations
    * @throws StationFullException 
    * @throws StationEmptyException 
    */
    @Override
    public void redistribute(List<Station> stations) throws StationFullException, StationEmptyException {
        List<Vehicule> vehicules = new ArrayList<>();

        // First collect all vehicles from all stations
        for (Station station : stations) {
            while (!station.isEmpty()) {
                Vehicule vehicule = station.giveVehicule();  // Remove vehicle from station
                vehicules.add(vehicule);    
            }
        }

        // Redistribute the vehicles randomly across stations
        Random random = new Random();
        while (!vehicules.isEmpty()) {
            int index = random.nextInt(stations.size());  // Get a random station index
            Station station = stations.get(index);
            if (!station.isFull() && !vehicules.isEmpty()) {
                // Only add the vehicle if the station is not full
                station.takeVehicule(vehicules.remove(0));  // Add vehicle to station
            }
           
        }
    }
}
