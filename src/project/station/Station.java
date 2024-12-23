package project.station;

import java.util.List;

import project.controlCenter.ControlCenter;
import project.station.Exception.StationEmptyException;
import project.station.Exception.StationFullException;
import project.vehicule.Vehicule;

import java.util.ArrayList;

/** Class for Station */
public class Station {

    /** ID of the station */
    private final int id;

    /** Capacity of the station */
    private final int capacity;

    /** List of vehicles currently at the station */
    private final List<Vehicule> vehicles;
    
    /** Observer for the station */
    private ControlCenter observer;

    /** Constructor for Station
     * @param id the ID of the station
     * @param capacity the capacity of the station
     */
    public Station(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.vehicles = new ArrayList<>();
        this.observer = ControlCenter.getInstance(); // Initialize observer
    }

    /** Gets the ID of the station 
     * @return the ID of the station
     */
    public int getId() {
        return id;
    }

    /** Gets the capacity of the station 
     * @return the capacity of the station
     */
    public int getCapacity() {
        return capacity;
    }

    /** Checks if the station is empty 
     * @return true if the station is empty, false otherwise
     */
    public boolean isEmpty() {
        return vehicles.isEmpty();
    }

    /** Checks if the station is full 
     * @return true if the station is full, false otherwise
     */
    public boolean isFull() {
        return vehicles.size() >= capacity;
    }

    /** Removes the last vehicle from the station and returns it
     * @return the last vehicle removed
     * @throws StationEmptyException if the station is empty
     */
    public Vehicule giveVehicule() throws StationEmptyException {
        if (isEmpty()) {
            throw new StationEmptyException("Station "+this.id+" is empty. Cannot remove vehicles.");
        }
        Vehicule removedVehicule = vehicles.remove(vehicles.size() - 1);
        notifyObserver(removedVehicule, false); // Notify observer of departure
        return removedVehicule;
    }

    /** Adds a vehicle to the station 
     * @param vehicule the vehicle to add
     * @throws StationFullException if the station is full
     */
    public void takeVehicule(Vehicule vehicule) throws StationFullException {
        if (isFull()) {
            throw new StationFullException("Station "+this.id+" is full. Cannot add more vehicles.");
        }
        vehicles.add(vehicule);
        notifyObserver(vehicule, true); // Notify observer of arrival
    }

    /** Gets the list of vehicles currently at the station
     * @return the list of vehicles at the station
     */
    public List<Vehicule> getVehicles() {
        return new ArrayList<>(vehicles); // Return a copy to avoid external modification
    }

    /** Notifies the observer of vehicle arrival or departure
     * @param vehicule the vehicle involved in the event
     * @param isArrival true if a vehicle arrives, false if it departs
     */
    private void notifyObserver(Vehicule vehicule, boolean isArrival) {
        observer.update(this, vehicule, isArrival);
    }

    /** Set the observer for the station
     * @param observer the observer to set
     */
    public void setObserver(ControlCenter observer) {
        this.observer = observer;
    }

    /** Method to get the observer of the station 
     * @return the observer of the station
     */
    public ControlCenter getObserver() {
        return observer;
    }
}
