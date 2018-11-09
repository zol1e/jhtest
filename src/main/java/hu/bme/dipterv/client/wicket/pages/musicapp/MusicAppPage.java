package hu.bme.dipterv.client.wicket.pages.musicapp;

import hu.bme.dipterv.client.wicket.components.home.HomePanel;
import hu.bme.dipterv.client.wicket.components.musicapp.MusicAppMain;
import hu.bme.dipterv.client.wicket.pages.MainPage;

public class MusicAppPage extends MainPage{
	
	public MusicAppPage() {
		super();
		
		//replace(new MusicAppMain(CONTENT_ID));
		add(new MusicAppMain("musicApp"));
		
		
		// Ha kiterjsztenénk ezzel a főoldalt, akkor csak egy helyre tudnánk "contentet" rakni.
		// replace() metódussal több kijelölt ponton is bele lehet írni az oldalba.
		
		/*
		Model<String> welcomeModel = Model.of("Welcome on home page");
		Label label = new Label("lblHomePage", welcomeModel);
		add(label);
		*/
	}
}
