package hu.bme.dipterv.client.wicket.components.home;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class HomePanel extends Panel {
	
	public HomePanel(String id) {
		super(id);
		
		Model<String> welcomeModel = Model.of("Welcome on home page");
		Label label = new Label("lblHomePage", welcomeModel);
		add(label);
	}
}
