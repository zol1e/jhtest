package hu.bme.dipterv.client.wicket;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

	private SignInPanel signInPanel;
	
	private Model<String> welcomeModel;
	
	public HomePage () {
		
		welcomeModel = Model.of("Welcome label");
		Label label = new Label("lblWelcome", welcomeModel);
		add(label);
		
		// Action link counts link clicks on works with onclick handler
        Link<Void> actionOnClickLink = new Link<Void>("linkLogout")
        {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
            	Session.get().invalidate();
            }
        };
        add(actionOnClickLink);
        
		signInPanel = new SignInPanel("signInPanel");
		add(signInPanel);
		
		WicketAuthenticatedWebSession session = (WicketAuthenticatedWebSession) Session.get();
		
		if(session.isSignedIn()) {
			signInPanel.setVisible(false);
			actionOnClickLink.setVisible(true);
			welcomeModel.setObject("Signed in " + session.getRoles());
		} else {
			signInPanel.setVisible(true);
			actionOnClickLink.setVisible(false);
			welcomeModel.setObject("Not signed in");
		}
		
	}
	
}