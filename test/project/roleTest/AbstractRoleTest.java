package project.roleTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.role.Human;
import project.station.Station;
import project.station.Exception.*;
import project.vehicule.bike.ClassicBike;

/** abstract class for testing Role */
public abstract class AbstractRoleTest {

    /** create a Role */
    protected Station station;

    /** Role */
    protected Human Human;
    
    /** create a Role */
    public abstract Human createRole();

    /** class StationMock */
    public class StationMock extends Station {
        public StationMock(int id, int capacity) {
            super(id, capacity);
        }
    }

    /** class VehiculeMock */
    public class VehiculeMock extends ClassicBike {
        public VehiculeMock(int id, int poids) {
            super(id, poids);
        }
    }
    

    /** initialize the Role */
    @BeforeEach
    public void initRole() {
        this.Human = this.createRole();
        this.station = new StationMock(1, 1);
    }

    /** test the getId method */
    @Test
    public void testGetId() {
        assertEquals(1, this.Human.getId());
    }

    /** test the getName method */
    @Test
    public void testGetName() {
        assertEquals("name", this.Human.getName());
    }
     
    /** test the getVehicule method */
    @Test
    public void testGetVehicule() {
        VehiculeMock vehicule = new VehiculeMock(1, 1);
        this.Human.setVehicule(vehicule);
        assertEquals(vehicule, this.Human.getVehicule());
        
    }

    /** test the interact method when the station is empty and the role has no vehicule */
    @Test
    public void testInteract_TakesVehicule_WhenNoVehicule() throws StationEmptyException {
        assertEquals(0, this.station.getVehicles().size());
        this.Human.interact(this.station);
        assertEquals(0, this.station.getVehicles().size());
        assertEquals(null, this.Human.getVehicule());
    }

    /** test the interact method when the station is not empty and the role has a vehicule */
    @Test
    public void testInteract_takeVehicule_WhenVehicule() throws StationEmptyException, StationFullException {
        VehiculeMock vehicule = new VehiculeMock(1, 1);
        this.station.takeVehicule(vehicule);
        assertEquals(1, this.station.getVehicles().size());
        this.Human.interact(this.station);
        assertEquals(0, this.station.getVehicles().size());
        assertEquals(vehicule, this.Human.getVehicule());
    }

    /** test the interact method when the station is not empty and the role has vehicule */
    @Test
    public void testInteract_ReturnVehicule_WhenVehicule() throws StationEmptyException, StationFullException {
        VehiculeMock vehicule = new VehiculeMock(1, 1);
        this.Human.setVehicule(vehicule);
        assertEquals(0, this.station.getVehicles().size());
        this.Human.interact(this.station);
        assertEquals(1, this.station.getVehicles().size());
        assertEquals(null, this.Human.getVehicule());
    }

    /** test the interact method when the station is full and the role has vehicule */
    @Test
    public void testInteract_ThrowsStationFullException_WhenStationFull() throws StationEmptyException, StationFullException {
        VehiculeMock vehicule = new VehiculeMock(1, 1);
        this.Human.setVehicule(vehicule);
        this.station.takeVehicule(new VehiculeMock(2, 1));
        assertEquals(1, this.station.getVehicles().size());
        this.Human.interact(this.station);
        assertEquals(1, this.station.getVehicles().size());
        assertEquals(vehicule, this.Human.getVehicule());
    }

}
