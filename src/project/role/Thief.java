package project.role;

import project.station.Station;
import project.station.Exception.StationEmptyException;
import project.vehicule.Vehicule;

/** Class of Thief */
public class Thief extends Human {

    /** Constructor of Thief
     * @param id id of the Thief
     * @param name name of the Thief
     */
    public Thief(int id, String name) {
        super(id, name);
    }

    /** Method to interact with a station
     * @param station the station to interact with
     */
    @Override
    public void interact(Station station) {
        try {
            // Thief takes a vehicle (stealing it) even if he already has one
            Vehicule stolenVehicule = station.giveVehicule();
            // If the thief already has a vehicle, the previous one is lost
            this.setVehicule(stolenVehicule);
            // The vehicle is stolen, so it is not updated at all here
        } catch (StationEmptyException e) {
            System.out.println("Station is empty. Cannot steal a vehicle.");
        }
    }
}
