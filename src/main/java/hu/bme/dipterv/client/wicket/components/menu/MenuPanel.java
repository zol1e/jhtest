package hu.bme.dipterv.client.wicket.components.menu;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.panel.Panel;

import hu.bme.dipterv.client.wicket.components.musicapp.MusicAppMain;
import hu.bme.dipterv.client.wicket.components.musicapp.band.viewer.BandViewer;
import hu.bme.dipterv.client.wicket.components.user.UserPanel;
import hu.bme.dipterv.client.wicket.pages.MainPage;
import hu.bme.dipterv.client.wicket.pages.article.ArticlePage;
import hu.bme.dipterv.client.wicket.pages.home.HomePage;
import hu.bme.dipterv.client.wicket.pages.musicapp.MusicAppPage;

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
        
        StatelessLink<Void> musicappLink = new StatelessLink<Void>("musicAppPanel")
        {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
				//getParent().getParent().replace(new MusicAppMain(MainPage.CONTENT_ID));
				//setResponsePage(BandViewer.class);
				/*MarkupContainer parent = getParent().getParent();
				parent.addOrReplace(new MusicAppMain(MainPage.CONTENT_ID));*/
				setResponsePage(MusicAppPage.class);
            }
        };
        add(musicappLink);
	}

}
