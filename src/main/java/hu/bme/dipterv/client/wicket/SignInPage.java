package hu.bme.dipterv.client.wicket;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.markup.html.WebPage;

public final class SignInPage extends WebPage
{
    /**
     * Constructor
     */
    public SignInPage()
    {
        // That is all you need to add a logon panel to your application with rememberMe
        // functionality based on Cookies. Meaning username and password are persisted in a Cookie.
        // Please see ISecuritySettings#getAuthenticationStrategy() for details.
        add(new SignInPanel("signInPanel"));
    }
}