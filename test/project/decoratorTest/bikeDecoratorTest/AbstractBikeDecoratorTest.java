package project.decoratorTest.bikeDecoratorTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.decorator.bikeDecorator.BikeDecorator;
import project.vehicule.bike.ClassicBike;


/** abstract class for testing BikeDecorator */
public abstract class AbstractBikeDecoratorTest {
    
    /** MockBike class */
    public class MockBike extends ClassicBike {
        public MockBike(int id, int weight) {
            super(id, weight);
        }
    }

    /** create a BikeDecorator */
    public abstract BikeDecorator createBikeDecorator(ClassicBike bike);

    /** BikeDecorator */
    protected BikeDecorator bikeDecorator;

    /** MockBike */
    protected MockBike mockBike;

    /** initialize the BikeDecorator */
    @BeforeEach
    public void initBikeDecorator() {
        this.mockBike = new MockBike(1, 10);
        this.bikeDecorator = createBikeDecorator(this.mockBike);
    }

    /** test the getDescription method */
    @Test
    public void testGetDescription() {
        assertEquals(mockBike.getDescription() + bikeDecorator.getDecoratorDescription(),
                bikeDecorator.getDescription());
    }

    /** test the getWeight method */
    @Test
    public void testGetWeight() {
        assertEquals(mockBike.getWeight() + bikeDecorator.getDecoratorWeight(), bikeDecorator.getWeight());
    }
}