package hu.bme.dipterv.client.wicket.pages;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import hu.bme.dipterv.client.wicket.components.menu.MenuPanel;
import hu.bme.dipterv.client.wicket.components.user.UserPanel;

public class MainPage extends WebPage {
	public static final String CONTENT_ID = "contentComponent";
	
	public MainPage () {
		
		Component userPanel = new UserPanel("userPanel");
		add(userPanel);
		
		Component menuPanel = new MenuPanel("menuPanel");
		add(menuPanel);
		
		// Extend helyett lehet "placeholdereket" létrehozni.
		// A leszármazott oldalak ezeket fogják lecserélni a replace(new component) metódussal
		add(new Label(CONTENT_ID, "Content area"));
	}
}