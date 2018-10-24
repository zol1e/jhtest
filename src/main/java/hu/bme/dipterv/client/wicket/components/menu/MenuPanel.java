package hu.bme.dipterv.client.wicket.components.menu;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.panel.Panel;

import hu.bme.dipterv.client.wicket.pages.article.ArticlePage;
import hu.bme.dipterv.client.wicket.pages.home.HomePage;

public class MenuPanel extends Panel {

	public MenuPanel(String id) {
		super(id);
		
		StatelessLink<Void> homePageLink = new StatelessLink<Void>("homePage")
        {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
				setResponsePage(HomePage.class);
            }
        };
        add(homePageLink);
        
        StatelessLink<Void> articlePageLink = new StatelessLink<Void>("articlePage")
        {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
				setResponsePage(ArticlePage.class);
            }
        };
        add(articlePageLink);
	}

}
