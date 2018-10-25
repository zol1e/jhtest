package hu.bme.dipterv.client.wicket.components.article;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.authentication.AuthenticationManager;

import hu.bme.dipterv.domain.Article;
import hu.bme.dipterv.service.ArticleService;
import hu.bme.dipterv.service.dto.ArticleDTO;

public class ArticlePanel extends Panel {
	
	@SpringBean
    private ArticleService articleService;
	
	@SuppressWarnings("serial")
	public ArticlePanel(String id) {
		super(id);
		
		Model<String> welcomeModel = Model.of("Articles");
		Label label = new Label("lblArticlePage", welcomeModel);
		add(label);
		
        final PageableListView<ArticleDTO> listView;
        add(listView = new PageableListView<ArticleDTO>("articles", new PropertyModel<>(articleService,
            "findAll"), 4)
        {
            @Override
            public void populateItem(final ListItem<ArticleDTO> listItem)
            {
                final ArticleDTO article = listItem.getModelObject();
                listItem.add(new Label("title", new Model<>(article.getTitle())));
                listItem.add(new Label("author", new Model<>(article.getWriterId())));
                listItem.add(new Label("content", new Model<>(article.getContent())));
            }
        });
	}

}