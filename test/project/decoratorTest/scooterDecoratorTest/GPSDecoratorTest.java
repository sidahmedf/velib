package project.decoratorTest.scooterDecoratorTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import project.decorator.electricalScooterdecorator.ElectricalScooterDecorator;
import project.decorator.electricalScooterdecorator.GPSDecorator;
import project.vehicule.electricalScooter.ElectricalScooter;

/** GPSDecorator class test */
public class GPSDecoratorTest extends AbstractScooterDecoratorTest {

    /** create a GPSDecorator */
    public ElectricalScooterDecorator createScooterDecorator(ElectricalScooter scooter) {
        return new GPSDecorator(scooter);
    }

    /** test the getDecoratorDescription method */
    @Test
    public void testGetDecoratorDescription() {
        assertEquals(" with GPS", this.scooterDecorator.getDecoratorDescription());
    }

    /** test the getDecoratorWeight method */
    @Test
    public void testGetDecoratorWeight() {
        assertEquals(1, this.scooterDecorator.getDecoratorWeight());
    }
    

}
