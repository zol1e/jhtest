package hu.bme.dipterv.client.wicket.pages.home;

import hu.bme.dipterv.client.wicket.components.home.HomePanel;
import hu.bme.dipterv.client.wicket.pages.MainPage;

public class HomePage extends MainPage {
	
	public HomePage() {
		super();
		
		replace(new HomePanel(CONTENT_ID));
		
		// Ha kiterjsztenénk ezzel a főoldalt, akkor csak egy helyre tudnánk "contentet" rakni.
		// replace() metódussal több kijelölt ponton is bele lehet írni az oldalba.
		
		/*
		Model<String> welcomeModel = Model.of("Welcome on home page");
		Label label = new Label("lblHomePage", welcomeModel);
		add(label);
		*/
	}
}
