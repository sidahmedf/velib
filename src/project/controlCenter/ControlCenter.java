package project.controlCenter;

import java.util.ArrayList;
import java.util.List;

import project.station.Station;
import project.station.Exception.StationEmptyException;
import project.station.Exception.StationFullException;
import project.strategy.RedistributionStrategy;
import project.strategy.RoundRobin;
import project.vehicule.Vehicule;

/** ControlCenter class */
public class ControlCenter {
    
    /** Singleton instance */
    private static final ControlCenter INSTANCE = new ControlCenter();

    /** List of stations */
    private List<Station> stations;

    /** Redistribution strategy */
    private RedistributionStrategy redStrategy;
    
    /** Constructor */
    public ControlCenter() {
        this.stations = new ArrayList<Station>();
        this.redStrategy = RoundRobin.getInstance(); // Default strategy
    }

    /** Singleton getter 
     * @return the singleton instance
     */
    public static ControlCenter getInstance() {
        return INSTANCE;
    }

    /** Getter for stations 
     * @return the list of stations
     */
    public List<Station> getStations() {
        return new ArrayList<>(stations); // Return a copy to avoid direct modification
    }

    /** Setter for stations 
     * @param stations the list of stations to set
     */
    public void setStations(List <Station> stations) {
        this.stations = stations ;
    }

    /** Add a station to the list 
     * @param station the station to add
     */
    public void addStation(Station station) {
        stations.add(station);
    }

    /** Remove a station from the list 
     * @param station the station to remove
     */
    public void removeStation(Station station) {
        stations.remove(station);
    }
    
    /** Setter for redistribution strategy 
     * @param strategy the strategy to set
     */
    public void setRedStrategy(RedistributionStrategy strategy){
        this.redStrategy = strategy;
    }

    /** Getter for redistribution strategy 
     * @return the current redistribution strategy
     */
    public RedistributionStrategy getRedStrategy() {
        return this.redStrategy;
    }
    

    /** Method to reallocate vehicules */
    public void reallocate() throws StationEmptyException, StationFullException {
        this.redStrategy.redistribute(this.stations);
    }
    
    /** Method to update the control center when a vehicule arrives or departs from a station 
     * @param station the station where the vehicule is
     * @param vehicule the vehicule that arrived or departed
     * @param isArrival true if the vehicule arrived, false if it departed
     */
    public void update(Station station, Vehicule vehicule, boolean isArrival) {
        String action = isArrival ? "arrived at" : "departed from";
        System.out.println("Vehicule " + vehicule.getId() + " has " + action + " station " + station.getId());
    }

    /** Method to clear all stations */
    public void clearStations() {
        this.stations.clear();
    }
}
