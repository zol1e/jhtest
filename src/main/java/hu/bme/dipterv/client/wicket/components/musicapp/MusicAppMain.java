package hu.bme.dipterv.client.wicket.components.musicapp;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import hu.bme.dipterv.client.wicket.components.musicapp.album.AlbumList;
import hu.bme.dipterv.client.wicket.components.musicapp.band.BandList;
import hu.bme.dipterv.client.wicket.components.musicapp.band.viewer.BandViewer;
import hu.bme.dipterv.client.wicket.extension.NavigablePanel;

public class MusicAppMain extends NavigablePanel {
	
	public static final String CONTENT = "contentPanelHolder";
	
	public MusicAppMain(String id) {
		super(id);
		
		this.navigateActionIdMap.put(new Long(1234), CONTENT);
		this.navigateActionIdMap.put(new Long(5678), CONTENT);
		
		Component bandListPanel = new BandList("bandListPanel");
		add(bandListPanel);
		
		Component albumListPanel = new AlbumList("albumListPanel");
		add(albumListPanel);
		
		// Extend helyett lehet "placeholdereket" létrehozni.
		// A leszármazott oldalak ezeket fogják lecserélni a replace(new component) metódussal
		add(new Label(CONTENT, ""));
		
		/*BandViewer bandViewer = new BandViewer(MusicAppMain.CONTENT);
		add(bandViewer);*/
	}
}
