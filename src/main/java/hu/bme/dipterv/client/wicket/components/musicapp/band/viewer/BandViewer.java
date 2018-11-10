package hu.bme.dipterv.client.wicket.components.musicapp.band.viewer;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import hu.bme.dipterv.client.wicket.components.musicapp.MusicAppMain;
import hu.bme.dipterv.client.wicket.components.musicapp.band.BandInfo;
import hu.bme.dipterv.client.wicket.components.musicapp.musician.MusicianList;
import hu.bme.dipterv.client.wicket.components.user.UserPanel;
import hu.bme.dipterv.client.wicket.pages.musicapp.MusicAppPage;

public class BandViewer extends Panel {

	public BandViewer(String id) {
		super(id);

		Component bandInfo = new BandInfo("bandInfoHolder");
		add(bandInfo);
		
		Component musicians = new MusicianList("musiciansHolder");
		add(musicians);
	}
	
}
