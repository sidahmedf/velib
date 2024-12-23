package project.decorator.electricalScooterdecorator;

import project.vehicule.electricalScooter.ElectricalScooter;

/** class of GPSDecorator */
public class GPSDecorator extends ElectricalScooterDecorator{
    
    /** ElectricalScooter to decorate */
    protected ElectricalScooter DecoratedElectricalScooter;

    /** Constructor of GPSDecorator
     * @param DecoratedScooter the ElectricalScooter to decorate
     */
    public GPSDecorator(ElectricalScooter DecoratedScooter){
        super(DecoratedScooter);
        this.decoratorWeight = 1;
        this.decoratorDescription = " with GPS";
    }

}
