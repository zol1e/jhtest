package hu.bme.dipterv.client.wicket.components.musicapp.band;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.panel.Panel;

import hu.bme.dipterv.client.wicket.components.musicapp.MusicAppMain;
import hu.bme.dipterv.client.wicket.components.musicapp.band.viewer.BandViewer;
import hu.bme.dipterv.client.wicket.pages.MainPage;
import hu.bme.dipterv.client.wicket.pages.musicapp.MusicAppPage;

public class BandList extends Panel {

	public BandList(String id) {
		super(id);
        
		StatelessLink<Void> bandNavigateActionLink = new StatelessLink<Void>("bandNavigateAction")
        {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
				MarkupContainer parent = getParent();
				MarkupContainer parent2 = parent.getParent();
				parent2.addOrReplace(new BandViewer(MusicAppMain.CONTENT));
            }
        };
        add(bandNavigateActionLink);
	}

}
