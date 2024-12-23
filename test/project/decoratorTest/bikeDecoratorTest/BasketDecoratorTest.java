package project.decoratorTest.bikeDecoratorTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import project.decorator.bikeDecorator.BasketDecorator;
import project.decorator.bikeDecorator.BikeDecorator;
import project.vehicule.bike.ClassicBike;

/** BasketDecorator class test */
public class BasketDecoratorTest extends AbstractBikeDecoratorTest {

    /** create a BasketDecorator */
    @Override
    public BikeDecorator createBikeDecorator(ClassicBike bike) {
        return new BasketDecorator(bike);
    }

    /** test the getDecoratorDescription method */
    @Test
    public void testGetDecoratorDescription() {
        assertEquals(" with a basket", bikeDecorator.getDecoratorDescription());
    }

    /** test the getDecoratorWeight method */
    @Test
    public void testGetDecoratorWeight() {
        assertEquals(2, bikeDecorator.getDecoratorWeight());
    }
}
