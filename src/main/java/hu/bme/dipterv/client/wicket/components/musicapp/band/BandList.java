package hu.bme.dipterv.client.wicket.components.musicapp.band;

import org.apache.wicket.markup.html.link.StatelessLink;

import hu.bme.dipterv.client.wicket.components.musicapp.band.viewer.BandViewer;
import hu.bme.dipterv.client.wicket.extension.NavigablePanel;
import hu.bme.dipterv.client.wicket.extension.NavigateAction;

public class BandList extends NavigablePanel {
	
	public BandList(String id) {
		super(id);
        
		/*Label link = new Label("bandNavigateAction");
		link.add((
				new AjaxEventBehavior("click") {
					private static final long serialVersionUID = 1L;
					
		            protected void onEvent(AjaxRequestTarget target) {
		            	NavigablePanel parent = (NavigablePanel) getParent();
						try {
							parent.navigate(new NavigateAction(BandViewer.class, new Long(1234)));
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
		
		StatelessLink<Void> bandNavigateActionLink = new StatelessLink<Void>("bandNavigateAction")
        {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
				NavigablePanel parent = (NavigablePanel) getParent();
				
				try {
					parent.navigate(new NavigateAction(BandViewer.class, new Long(1234)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//parent2.addOrReplace(new BandViewer(MusicAppMain.CONTENT));
            }
        };
        add(bandNavigateActionLink);
	}
}
