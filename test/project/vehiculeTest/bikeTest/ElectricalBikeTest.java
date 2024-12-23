package project.vehiculeTest.bikeTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import project.vehicule.Vehicule;
import project.vehicule.bike.ElectricalBike;

/** class for testing ElectricalBike */
public class ElectricalBikeTest extends AbstractBikeTest {
   
    /** create a ElectricalBike */
    public Vehicule createVehicule(){
        return new ElectricalBike(1,1);
    } 

    /** test the getDescription method */
    @Test
    public void testGetDescription() {
        assertEquals("An electrical bike", this.vehicule.getDescription());
    }



}
