package project.role;

import project.station.Station;
import project.vehicule.Vehicule;

/** class of Role */
public abstract class Human{

    /** id of the role */
    private int id;

    /** name of the role */
    private String name;

    /** vehicule of the role */
    private Vehicule vehicule;

    /** Constructor of Role
     * @param id id of the role
     * @param name name of the role
     */
    public Human(int id, String name) {
        this.id = id;
        this.name = name;
        this.vehicule = null;
    }

    /** Method to get the id of the role
     * @return the id of the role
     */
    public int getId() {
        return id;
    }

    /** Method to get the name of the role
     * @return the name of the role
     */
    public String getName() {
        return name;
    }

    /** Method to get the vehicule of the role
     * @return the vehicule of the role
     */
    public Vehicule getVehicule() {
        return vehicule;
    }

    /** Method to set the vehicule of the role
     * @param vehicule the vehicule of the role
     */
    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    /** Method to interact with a station
     * @param station the station to interact with
     */
    public abstract void interact(Station station);
}
