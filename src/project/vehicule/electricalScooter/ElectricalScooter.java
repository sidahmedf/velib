package project.vehicule.electricalScooter;

import project.vehicule.Vehicule;

/** Class of electrical scooter */
public class ElectricalScooter extends Vehicule{

    /** batteryCapacity of the bike*/
    private int batteryCapacity;

    /** Constructor of Bike
     * @param id id of the bike
     * @param weight weight of the bike
     * @param batteryCapacity batteryCapacity of the electrical scooter
     */
    public ElectricalScooter(int id, int weight, int batteryCapacity){
        super(id, weight);
        this.batteryCapacity = batteryCapacity;
    }

    /** Method to get the batteryCapacity of the electrical scooter
     * @return the batteryCapacity of the electrical scooter
     */
    public int getBatteryCapacity(){
        return batteryCapacity;
    }

    /** Method to get the description of the electrical scooter
     * @return the description of the electrical scooter
     */
    public String getDescription(){
        return "An electrical scooter";
    }


}