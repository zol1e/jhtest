package hu.bme.dipterv.client.wicket;

import java.io.Serializable;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.hibernate.validator.internal.util.privilegedactions.GetMethodFromPropertyName;

public class HomePage extends WebPage {

	private SignInPanel signInPanel;
	
	private Model<String> welcomeModel;
	
	public HomePage () {
		
		welcomeModel = Model.of("Welcome label");
		Label label = new Label("lblWelcome", welcomeModel);
		add(label);
		
		signInPanel = new SignInPanel("signInPanel");
		add(signInPanel);
		
		LogoutButton btnLogout = new LogoutButton("btnLogout");
		add(btnLogout);
		
		WicketAuthenticatedWebSession session = (WicketAuthenticatedWebSession) Session.get();
		
		if(session.isSignedIn()) {
			signInPanel.setVisible(false);
			welcomeModel.setObject("Signed in " + session.getRoles());
		} else {
			signInPanel.setVisible(true);
			welcomeModel.setObject("Not signed in");
		}
		
	}
	
	public class LogoutButton extends Button {

		public LogoutButton(String id) {
			super(id);
		}
		
		@Override
		public void onSubmit() {
			Session.get().invalidate();
			super.onSubmit();
		}
		
		@Override
		public IModel<String> getLabel() {
			return Model.of("My logout button");
		}
		
	}
	
}