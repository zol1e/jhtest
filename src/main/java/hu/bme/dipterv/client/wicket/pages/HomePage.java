package hu.bme.dipterv.client.wicket.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

public class HomePage extends MainPage {
	
	public HomePage() {
		super();
		
		Model<String> welcomeModel = Model.of("Welcome on home page");
		Label label = new Label("lblHomePage", welcomeModel);
		add(label);
	}
}
