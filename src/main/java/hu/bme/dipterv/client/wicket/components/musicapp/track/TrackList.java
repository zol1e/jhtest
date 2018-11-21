package hu.bme.dipterv.client.wicket.components.musicapp.track;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;

import hu.bme.dipterv.client.wicket.components.musicapp.band.viewer.BandViewer;
import hu.bme.dipterv.client.wicket.extension.NavigablePanel;
import hu.bme.dipterv.client.wicket.extension.NavigateAction;

public class TrackList extends NavigablePanel {

	public TrackList(String id) {
		super(id);
		
		Link<Void> bandNavigateActionLink = new Link<Void>("bandListNavigateAction")
        {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
				NavigablePanel parent = (NavigablePanel) getParent();
				
				try {
					parent.navigate(new NavigateAction(BandViewer.class, new Long(9101)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
        add(bandNavigateActionLink);
	}

}
