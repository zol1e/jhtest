package hu.bme.dipterv.client.wicket.components.musicapp.musician;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;

import hu.bme.dipterv.client.wicket.components.musicapp.track.TrackList;
import hu.bme.dipterv.client.wicket.extension.NavigablePanel;
import hu.bme.dipterv.client.wicket.extension.NavigateAction;

public class MusicianList extends NavigablePanel {

	public MusicianList(String id) {
		super(id);
		
		Link<Void> trackNavigateActionLink = new Link<Void>("trackNavigateAction")
        {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
				System.out.println();
				
				NavigablePanel parent = (NavigablePanel) getParent();
				
				try {
					parent.navigate(new NavigateAction(TrackList.class, new Long(5678)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
        add(trackNavigateActionLink);
	}

}
