package project.vehicule.bike;
/** class of electrical bike, extends Bike interface */
public class ElectricalBike extends Bike {


    /** Constructor of ElectricalBike
     * @param id id of the bike
     * @param weight weight of the bike
     */
    public ElectricalBike(int id, int weight) {
        super(id, weight, 80);   
    }

    /** Method to get the description of the bike
     * @return the description of the bike
     */
    public String getDescription() {
        return "An electrical bike" ;
    }

}