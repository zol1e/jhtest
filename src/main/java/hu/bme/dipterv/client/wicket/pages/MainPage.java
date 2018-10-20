package hu.bme.dipterv.client.wicket.pages;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import hu.bme.dipterv.client.wicket.components.MenuPanel;
import hu.bme.dipterv.client.wicket.components.UserPanel;

public class MainPage extends WebPage {

	private static final long serialVersionUID = 1L;
	
	private Component userPanel;
	
	private Component menuPanel;
	
	public MainPage () {

		userPanel = new UserPanel("userPanel");
		add(userPanel);
		
		menuPanel = new MenuPanel("menuPanel");
		add(menuPanel);
		
	}
}