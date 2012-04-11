package ejb.security;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


/**
 * Session Bean implementation class TestBean
 */
@Stateless
@LocalBean
public class TestBean {

    /**
     * Default constructor. 
     */
    public TestBean() {
    }

    public String echo(String whatToEcho) {
		return whatToEcho;
	}

    public String goodUserEcho(String whatToEcho) {
		return whatToEcho;
	} 

    public String superUserEcho(String whatToEcho) {
		return whatToEcho;
	} 
    
}
