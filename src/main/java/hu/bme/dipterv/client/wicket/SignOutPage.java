package hu.bme.dipterv.client.wicket;

import org.apache.wicket.markup.html.WebPage;

public class SignOutPage extends WebPage {
	
    public SignOutPage()
    {
        getSession().invalidate();
    }
}
