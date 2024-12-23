package project.roleTest;

import project.role.Client;
import project.role.Human;

/** class for testing Client */
public class ClientTest extends AbstractRoleTest {
    
    /** create a Client */
    public Human createRole(){
        return new Client(1, "name");
    }

}
