package project.vehiculeTest.electricalScooterTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import project.vehiculeTest.AbstractVehiculeTest;
import project.vehicule.Vehicule;
import project.vehicule.electricalScooter.ElectricalScooter;

/** class for testing ElectricalScooter */
public class ElectricalScooterTest extends AbstractVehiculeTest {

    /** create a ElectricalScooter */
    public Vehicule createVehicule(){
        return new ElectricalScooter(1,1,1);
    }

    /** test the getDescription method */
    @Test
    public void testGetDescription() {
        assertEquals("An electrical scooter", this.vehicule.getDescription());
    }

    /** test the getBatteryCapacity method */
    @Test
    public void testGetBatteryCapacity() {
        assertEquals(1, ((ElectricalScooter)this.vehicule).getBatteryCapacity());
    }

}
