package hu.bme.dipterv.client.wicket;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class TestAuthenticatedWebSession extends AuthenticatedWebSession
{
	private static final long serialVersionUID = 1L;

	@SpringBean
    private AuthenticationManager authenticationManager;
	
    /**
     * Construct.
     * 
     * @param request
     *            The current request object
     */
    public TestAuthenticatedWebSession(Request request)
    {
        super(request);
        Injector.get().inject(this);
    }

    @Override
    public boolean authenticate(final String username, final String password)
    {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // Check username and password
        return authenticate.isAuthenticated();
    }

    @Override
    public Roles getRoles()
    {
        if (isSignedIn())
        {
            // If the user is signed in, they have these roles
            return new Roles(Roles.ADMIN);
        }
        return null;
    }
}