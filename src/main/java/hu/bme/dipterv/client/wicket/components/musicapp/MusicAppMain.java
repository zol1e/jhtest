package hu.bme.dipterv.client.wicket.components.musicapp;

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
		this.navigateActionIdMap.put(new Long(9101), CONTENT);
		
		BandList bandListPanel = new BandList("bandListPanel");
		add(bandListPanel);
		
		AlbumList albumListPanel = new AlbumList("albumListPanel");
		add(albumListPanel);
		
		BandViewer bandViewerToContent = new BandViewer("contentPanelHolder");
		add(bandViewerToContent);
		
		// Extend helyett lehet "placeholdereket" létrehozni.
		// A leszármazott oldalak ezeket fogják lecserélni a replace(new component) metódussal
		//bandViewerToContent = new BandViewer("contentPanelHolderBandViewer", true);
		//bandViewerToContent = new BandViewer("contentPanelHolder");
		//add(bandViewerToContent)
		
		/*addPanelToHolder(MusicAppMain.CONTENT, bandViewerToContent);
		navigateActionIdMap.put(new Long(1234), new NavigateMapping(MusicAppMain.CONTENT, bandViewerToContent));
		navigateActionIdMap.put(new Long(9101), new NavigateMapping(MusicAppMain.CONTENT, bandViewerToContent));
		
		//trackListToContent = new TrackList("contentPanelHolderTrackList", true);
		trackListToContent = new TrackList("contentPanelHolder");
		addPanelToHolder(MusicAppMain.CONTENT, trackListToContent);
		navigateActionIdMap.put(new Long(5678), new NavigateMapping(MusicAppMain.CONTENT, trackListToContent));*/

		//add(bandViewerToContent);
		//add(trackListToContent);
		
		/*BandViewer bandViewer = new BandViewer(MusicAppMain.CONTENT, true);
		add(bandViewer);*/
	}
}
