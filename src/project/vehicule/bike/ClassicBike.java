package project.vehicule.bike;
/** class of classic bike, extends Bike interface */
public class ClassicBike extends Bike {

    /** Constructor of ClassicBike
     * @param id id of the bike
     * @param weight weight of the bike
     */
    public ClassicBike(int id, int weight) {
        super(id, weight, 80);
    }

    /** Method to get the description of the bike
     * @return the description of the bike
     */
    public String getDescription() {
        return "A classic bike" ;
    }

}