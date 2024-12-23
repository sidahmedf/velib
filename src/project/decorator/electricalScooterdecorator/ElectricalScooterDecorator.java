package project.decorator.electricalScooterdecorator;
import project.vehicule.electricalScooter.ElectricalScooter;

/** class of ElectricalScooterDecorator */
public abstract class ElectricalScooterDecorator extends ElectricalScooter {

    /** ElectricalScooter to decorate */
   protected ElectricalScooter decoratedElectricalScooter;

   /** the weight of the decorator */
   protected int decoratorWeight;

   /** the description of the decorator */
   protected String decoratorDescription;

   /** Constructor of ElectricalScooterDecorator
    * @param DecoratedScooter the ElectricalScooter to decorate
    */
   public ElectricalScooterDecorator(ElectricalScooter DecoratedScooter) {
      super(DecoratedScooter.getId(), DecoratedScooter.getWeight(), DecoratedScooter.getBatteryCapacity());
      this.decoratedElectricalScooter = DecoratedScooter;
   }

   /** Method to get the description of the ElectricalScooter
    * @return the description of the ElectricalScooter
    */
   public String getDescription() {
      return decoratedElectricalScooter.getDescription() + decoratorDescription;
   }

   /** Method to get the weight of the ElectricalScooter
    * @return the weight of the ElectricalScooter
    */
   public int getWeight() {
      return decoratedElectricalScooter.getWeight() + decoratorWeight;
   }

   /** Method to get the description of the decorator
    * @return the description of the decorator
    */
   public String getDecoratorDescription() {
      return decoratorDescription;
   }

   /** Method to get the weight of the decorator
    * @return the weight of the decorator
    */
   public int getDecoratorWeight() {
      return decoratorWeight;
   }


}