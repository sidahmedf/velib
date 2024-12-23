package project.vehicule.bike;

import project.vehicule.Vehicule;

/** Class of Bike */
public abstract class Bike extends Vehicule{

    /** seatHeight of the bike*/
    private int seatHeight;

    /** Constructor of Bike
     * @param id id of the bike
     * @param weight weight of the bike
     * @param seatHeight seatHeight of the bike
     */
    public Bike(int id, int weight, int seatHeight){
        super(id, weight);
        this.seatHeight = seatHeight;
    }

    /** Method to get the seatHeight of the bike
     * @return the seatHeight of the bike
     */
    public int getSeatHeight(){
        return seatHeight;
    }


}