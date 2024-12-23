package project.decorator.bikeDecorator;

import project.vehicule.bike.Bike;

/** BasketDecorator class that extends BikeDecorator class*/
public class BasketDecorator extends BikeDecorator{

    /** the bike to decorate */
    protected Bike Decoratedbike;


    /** Constructor of BasketDecorator
     * @param Decoratedbike the bike to decorate
     */
    public BasketDecorator(Bike Decoratedbike) {
        super(Decoratedbike);
        this.decoratorWeight = 2;
        this.decoratorDescription = " with a basket";
    }

    
}