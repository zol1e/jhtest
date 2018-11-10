package hu.bme.dipterv.client.wicket.pages.musicapp;

import org.apache.wicket.Component;

import hu.bme.dipterv.client.wicket.components.home.HomePanel;
import hu.bme.dipterv.client.wicket.components.musicapp.MusicAppMain;
import hu.bme.dipterv.client.wicket.components.musicapp.album.AlbumList;
import hu.bme.dipterv.client.wicket.components.musicapp.band.BandList;
import hu.bme.dipterv.client.wicket.components.musicapp.band.viewer.BandViewer;
import hu.bme.dipterv.client.wicket.pages.MainPage;

public class MusicAppPage extends MainPage {
	
	public MusicAppPage() {
		super();
		
		replace(new MusicAppMain(CONTENT_ID));
		//add(new MusicAppMain("musicApp"));
		
		/*Component bandListPanel = new BandList("bandListPanel");
		add(bandListPanel);
		
		Component albumListPanel = new AlbumList("albumListPanel");
		add(albumListPanel);*/
		
		// Extend helyett lehet "placeholdereket" létrehozni.
		// A leszármazott oldalak ezeket fogják lecserélni a replace(new component) metódussal
		//add(new Label(CONTENT, "Content area"));
		
		//BandViewer bandViewer = new BandViewer("contentPanelHolder");
		//add(bandViewer);
		
		// Ha kiterjsztenénk ezzel a főoldalt, akkor csak egy helyre tudnánk "contentet" rakni.
		// replace() metódussal több kijelölt ponton is bele lehet írni az oldalba.
		
		/*
		Model<String> welcomeModel = Model.of("Welcome on home page");
		Label label = new Label("lblHomePage", welcomeModel);
		add(label);
		*/
	}
}
