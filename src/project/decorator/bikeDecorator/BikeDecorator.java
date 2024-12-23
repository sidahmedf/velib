package project.decorator.bikeDecorator;
import project.vehicule.bike.Bike;

/** class of BikeDecorator */
public abstract class BikeDecorator extends Bike {

   /** Bike to decorate */
   protected Bike decoratedBike; 

   /** the weight of the decorator */
   protected int decoratorWeight;

   /** the description of the decorator */
   protected String decoratorDescription;

   /** Constructor of BikeDecorator
    * @param Decoratedbike the bike to decorate
    */
   public BikeDecorator(Bike Decoratedbike){
      super(Decoratedbike.getId(), Decoratedbike.getWeight(), Decoratedbike.getSeatHeight());
      this.decoratedBike = Decoratedbike;
   }

   /** Method to get the description of the bike
    * @return the description of the bike
    */
   public String getDecoratorDescription(){
      return decoratorDescription;
   }

   /** Method to get the weight of the bike
    * @return the weight of the bike
    */
   public int getDecoratorWeight(){
      return decoratorWeight;
   }

   /** Method to get the description of the bike with a basket
     * @return the description of the bike with a basket
     */
    public String getDescription(){
      return decoratedBike.getDescription() + decoratorDescription;
  }
  
  /** Method to get the weight of the bike with a basket
   * @return the weight of the bike with a basket
   */
  public int getWeight(){
      return decoratedBike.getWeight() + decoratorWeight;
  }


}