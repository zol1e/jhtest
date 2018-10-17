package hu.bme.dipterv.client.wicket;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import hu.bme.dipterv.security.AuthoritiesConstants;
import hu.bme.dipterv.security.SecurityUtils;

public class WicketAuthenticatedWebSession extends AuthenticatedWebSession
{
	private static final long serialVersionUID = 1L;

	@SpringBean
    private AuthenticationManager authenticationManager;
	
	private Authentication authentication;
	
    /**
     * Construct.
     * 
     * @param request
     *            The current request object
     */
    public WicketAuthenticatedWebSession(Request request)
    {
        super(request);
        Injector.get().inject(this);
    }

    @Override
    public boolean authenticate(final String username, final String password)
    {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        
        authentication = authenticationManager.authenticate(authenticationToken);
        
        // Check username and password
        return authentication.isAuthenticated();
    }

	@Override
	public Roles getRoles() {
		if(isSignedIn()) {
			return new Roles(authentication.getAuthorities().iterator().next().getAuthority());
		}
		return null;
	}
}