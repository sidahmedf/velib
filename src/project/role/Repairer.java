package project.role;

import project.station.Station;
import project.station.Exception.StationEmptyException;
import project.station.Exception.StationFullException;
import project.vehicule.Vehicule;

/** Class of Repairer */
public class Repairer extends Human {

    /** Constructor of Repairer
     * @param id id of the Repairer
     * @param name name of the Repairer
     */
    public Repairer(int id, String name) {
        super(id, name);
    }

    /** Method to interact with a station
     * @param station the station to interact with
     */
    @Override
    public void interact(Station station) {
        if (this.getVehicule() == null) {
            try {
                // Repairer takes a vehicle for repair
                Vehicule vehicule = station.giveVehicule();
                this.setVehicule(vehicule);
                vehicule.setoutService(true);  // Set vehicle as out of service
                vehicule.setusage(0);          // Reset usage count while out of service
            } catch (StationEmptyException e) {
                System.out.println("Station "+station.getId()+" empty. Cannot take a vehicle.");
            }
        } else {
            try {
                // Repairer returns the vehicle after repair
                Vehicule vehicule = this.getVehicule();
                vehicule.setoutService(false);  // Set vehicle back to service
                station.takeVehicule(vehicule); // Return the vehicle to the station
                this.setVehicule(null);         // After returning, the repairer no longer has a vehicle
            } catch (StationFullException e) {
                System.out.println("Station "+station.getId()+"is full. Cannot return the vehicle.");
            }
        }
    }
}
