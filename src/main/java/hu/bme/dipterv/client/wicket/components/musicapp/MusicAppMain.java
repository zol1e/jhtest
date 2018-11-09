package hu.bme.dipterv.client.wicket.components.musicapp;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.panel.Panel;

import hu.bme.dipterv.client.wicket.components.musicapp.album.AlbumList;
import hu.bme.dipterv.client.wicket.components.musicapp.band.BandList;
import hu.bme.dipterv.client.wicket.components.musicapp.band.viewer.BandViewer;

public class MusicAppMain extends Panel {
	
	public static final String CONTENT = "contentPanelHolder";
	
	public MusicAppMain(String id) {
		super(id);
		
		Component bandListPanel = new BandList("bandListPanel");
		add(bandListPanel);
		
		Component albumListPanel = new AlbumList("albumListPanel");
		add(albumListPanel);
		
		// Extend helyett lehet "placeholdereket" létrehozni.
		// A leszármazott oldalak ezeket fogják lecserélni a replace(new component) metódussal
		//add(new Label(CONTENT, "Content area"));
		
		Component bandViewer = new BandViewer(MusicAppMain.CONTENT);
		add(bandViewer);
	}
}
