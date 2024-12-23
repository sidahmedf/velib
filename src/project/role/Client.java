package project.role;

import project.station.Station;
import project.station.Exception.StationEmptyException;
import project.station.Exception.StationFullException;

/** Class of Client */
public class Client extends Human {

    /** Constructor of Client
     * @param id id of the Client
     * @param name name of the Client
     */
    public Client(int id, String name) {
        super(id, name);
    }

    /** Method to interact with a station
     * @param station the station to interact with
     */
    @Override
    public void interact(Station station) {
        if (this.getVehicule() == null) {
            try {
                // Client takes a vehicle if they don't have one
                this.setVehicule(station.giveVehicule());
            } catch (StationEmptyException e) {
                System.out.println("Station "+station.getId()+" is empty. Cannot take a vehicle.");
            }
        } else {
            try {
                // Client returns the vehicle if they have one
                station.takeVehicule(this.getVehicule());
                this.setVehicule(null); // After returning, they no longer have a vehicle
            } catch (StationFullException e) {
                System.out.println("Station "+station.getId()+" is full. Cannot return the vehicle.");
            }
        }
    }
}
