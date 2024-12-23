package project.vehiculeTest.bikeTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import project.vehicule.Vehicule;
import project.vehicule.bike.ClassicBike;

/** class for testing ClassicBike */
public class ClassicBikeTest extends AbstractBikeTest {
   
    /** create a ClassicBike */
    public Vehicule createVehicule(){
        return new ClassicBike(1,1);
    } 

    /** test the getDescription method */
    @Test
    public void testGetDescription() {
        assertEquals("A classic bike", this.vehicule.getDescription());
    }
}

