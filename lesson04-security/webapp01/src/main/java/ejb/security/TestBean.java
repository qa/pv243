package ejb.security;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * Session Bean implementation class TestBean
 */
@Stateless
@LocalBean
@DeclareRoles({"superuser","gooduser"})
@SecurityDomain("test")
public class TestBean {

    /**
     * Default constructor. 
     */
    public TestBean() {
    }

    @PermitAll
    public String echo(String whatToEcho) {
		return whatToEcho;
	}

    @RolesAllowed({"gooduser"})
    public String goodUserEcho(String whatToEcho) {
		return whatToEcho;
	} 

    @RolesAllowed({"superuser"})
    public String superUserEcho(String whatToEcho) {
		return whatToEcho;
	} 
    
}
