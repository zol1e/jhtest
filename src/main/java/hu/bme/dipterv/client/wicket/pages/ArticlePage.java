package hu.bme.dipterv.client.wicket.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

public class ArticlePage extends MainPage {
	
	public ArticlePage() {
		super();
		
		Model<String> welcomeModel = Model.of("Welcome on article page");
		Label label = new Label("lblArticlePage", welcomeModel);
		add(label);
	}

}
