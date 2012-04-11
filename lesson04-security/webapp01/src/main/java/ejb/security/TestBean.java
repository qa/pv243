package ejb.security;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
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

	@Resource
	SessionContext ctx;
	
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
    
    @PermitAll
    public String sessionContextInfo() {
    	return  "-----------------------------------------------------------------\n" 
    			+ ctx.getCallerPrincipal().toString() + "\n"
    			+ "caller is in gooduser role = " + ctx.isCallerInRole("gooduser") + "\n"
    			+ "-----------------------------------------------------------------\n"
    			;
    }
}
