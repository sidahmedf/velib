package project.roleTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import project.role.Human;
import project.role.Thief;
import project.station.Exception.StationEmptyException;
import project.station.Exception.StationFullException;

/** class for testing Thief */
public class ThiefTest extends AbstractRoleTest {

    /** create a Thief */
    @Override
    public Human createRole() {
        return new Thief(1, "name");
    }

    /** test the interact method when the station is empty and the thief has vehicule */
    @Test
    public void testInteract_ReturnVehicule_WhenVehicule() throws StationEmptyException, StationFullException {
        VehiculeMock vehicule = new VehiculeMock(1, 1);
        this.Human.setVehicule(vehicule);
        assertEquals(0, this.station.getVehicles().size());
        this.Human.interact(this.station);
        assertEquals(0, this.station.getVehicles().size());
        assertEquals(vehicule, this.Human.getVehicule());
    }

    /** test the interact method when the station is not empty and the thief has vehicule */
    @Test
    public void testInteract_ThrowsStationFullException_WhenStationFull() throws StationEmptyException, StationFullException {
        VehiculeMock vehicule = new VehiculeMock(1, 1);
        this.Human.setVehicule(new VehiculeMock(2, 1));
        this.station.takeVehicule(vehicule);
        assertEquals(1, this.station.getVehicles().size());
        this.Human.interact(this.station);
        assertEquals(0, this.station.getVehicles().size()); // The thief has stolen the vehicule
        assertEquals(vehicule, this.Human.getVehicule()); // the new vehicule is the stolen one and the previous vehicule is lost
    }
}
