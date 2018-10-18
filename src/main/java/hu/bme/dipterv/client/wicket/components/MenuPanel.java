package hu.bme.dipterv.client.wicket.components;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class MenuPanel extends Panel {

	public MenuPanel(String id) {
		super(id);
		
        Link<Void> homePageLink = new Link<Void>("homePage")
        {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {

            }
        };
        add(homePageLink);
        
        Link<Void> articlePageLink = new Link<Void>("articlePage")
        {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {

            }
        };
        add(articlePageLink);
	}

}
