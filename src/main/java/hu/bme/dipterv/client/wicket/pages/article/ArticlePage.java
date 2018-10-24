package hu.bme.dipterv.client.wicket.pages.article;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import hu.bme.dipterv.client.wicket.components.article.ArticlePanel;
import hu.bme.dipterv.client.wicket.pages.MainPage;

public class ArticlePage extends MainPage {
	
	public ArticlePage() {
		super();

		replace(new ArticlePanel(CONTENT_ID));
		
		/*Model<String> welcomeModel = Model.of("Welcome on article page");
		Label label = new Label("lblArticlePage", welcomeModel);
		add(label);*/
	}

}
