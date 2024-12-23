package project.vehicule;

import project.station.Station;

/** Class of Vehicule */
public abstract class Vehicule  {

    /** id of the vehicule */
    private int id;

    /** number of location of the vehicule */
    private int usage;

    /** if the vehicule is out of service */
    private boolean outService;

    /** weight of the vehicule */
    private int poids;

    /** station of the vehicule */
    private Station station;


    /** Constructor of Vehicule 
     * @param id id of the vehicule
     * @param poids weight of the vehicule
     */
    public Vehicule(int id, int poids) {
        this.id = id;
        this.usage = 0;
        this.outService = false;
        this.poids = poids;
        this.station = null;
    }

    /** Method to get the id of the vehicule
     * @return the id of the vehicule
     */
    public int getId() {
        return id;
    }

    /** Method to get the number of location of the vehicule
     * @return the number of location of the vehicule
     */
    public int getUsage() {
        return usage;
    }

    /** Method to set the number of location of the vehicule
     * @param usage the number of location of the vehicule
     */
    public void setusage(int usage) {
        this.usage = usage;
    }

    /** Method to know if the vehicule is out of service
     * @return if the vehicule is out of service
     */
    public boolean isOutService() {
        return outService;
    }

    /** Method to set the vehicule out of service
     * @param outService if the vehicule is out of service
     */
    public void setoutService(boolean outService) {
        this.outService = outService;
    }

    /** Method to get the weight of the vehicule
     * @return the weight of the vehicule
     */
    public int getWeight() {
        return poids;
    }

    /** Method to get the station of the vehicule
     * @return the station of the vehicule
     */
    public Station getStation() {
        return station;
    }

    /** Method to set the station of the vehicule
     * @param station the station of the vehicule
     */
    public void setStation(Station station) {
        this.station = station;
    }

    /** Method to get the description of the vehicule
     * @return the description of the vehicule
     */
    public abstract String getDescription();
}
