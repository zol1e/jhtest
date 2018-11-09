package hu.bme.dipterv.client.wicket.components.musicapp.band.viewer;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import hu.bme.dipterv.client.wicket.components.musicapp.band.BandInfo;
import hu.bme.dipterv.client.wicket.components.user.UserPanel;

public class BandViewer extends Panel {

	public BandViewer(String id) {
		super(id);

		//BandInfo bandInfo = new BandInfo("bandInfoHolder");
		add(new BandInfo("bandInfoHolder"));
		
		/*Component musicians = new MusicianList("musiciansHolder");
		add(musicians);*/
	}
	
}
