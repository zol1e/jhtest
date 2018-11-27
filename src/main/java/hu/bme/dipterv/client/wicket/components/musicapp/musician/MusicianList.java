package hu.bme.dipterv.client.wicket.components.musicapp.musician;

import org.apache.wicket.markup.html.link.StatelessLink;

import hu.bme.dipterv.client.wicket.components.musicapp.track.TrackList;
import hu.bme.dipterv.client.wicket.extension.NavigablePanel;
import hu.bme.dipterv.client.wicket.extension.NavigateAction;

public class MusicianList extends NavigablePanel {
	
	public MusicianList(String id) {
		super(id);
		
		/*Label link = new Label("trackNavigateAction");
		link.add((
				new AjaxEventBehavior("click") {
					private static final long serialVersionUID = 1L;

					protected void onEvent(AjaxRequestTarget target) {
		            	NavigablePanel parent = (NavigablePanel) getParent();
						try {
							parent.navigate(new NavigateAction(TrackList.class, new Long(5678)));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
				}
        ));
		
		add(link);*/
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		StatelessLink<Void> trackNavigateActionLink = new StatelessLink<Void>("trackNavigateAction")
        {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
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
