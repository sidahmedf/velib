package project.decoratorTest.scooterDecoratorTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.decorator.electricalScooterdecorator.*;
import project.vehicule.electricalScooter.*;

/** abstract class for testing ScooterDecorator */
public abstract class AbstractScooterDecoratorTest {

    /** MockScooter class */
    public class MockScooter extends ElectricalScooter {
        public MockScooter(int id, int weight) {
            super(id, weight, 1);
        }
    }

    /** create a ScooterDecorator */
    public abstract ElectricalScooterDecorator createScooterDecorator(ElectricalScooter scooter);

    /** ScooterDecorator */
    protected ElectricalScooterDecorator scooterDecorator;

    /** MockScooter */
    protected MockScooter mockScooter;

    /** initialize the ScooterDecorator */
    @BeforeEach
    public void initScooterDecorator() {
        this.mockScooter = new MockScooter(1, 10);
        this.scooterDecorator = createScooterDecorator(this.mockScooter);
    }

    /** test the getDescription method */
    @Test
    public void testGetDescription() {
        assertEquals(mockScooter.getDescription() + scooterDecorator.getDecoratorDescription(),
                scooterDecorator.getDescription());
    }

    /** test the getWeight method */
    @Test
    public void testGetWeight() {
        assertEquals(mockScooter.getWeight() + scooterDecorator.getDecoratorWeight(), scooterDecorator.getWeight());
    }


}
