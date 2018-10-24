package hu.bme.dipterv.client.wicket.components.article;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class ArticlePanel extends Panel {
	
	public ArticlePanel(String id) {
		super(id);
		
		Model<String> welcomeModel = Model.of("Welcome on article page");
		Label label = new Label("lblArticlePage", welcomeModel);
		add(label);
	}

}