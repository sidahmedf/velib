package project.roleTest;

import project.role.Human;
import project.role.Repairer;

/** class for testing Repairer */
public class RepairerTest extends AbstractRoleTest {

    /** create a Repairer */
    public Human createRole(){
        return new Repairer(1, "name");
    }

}
