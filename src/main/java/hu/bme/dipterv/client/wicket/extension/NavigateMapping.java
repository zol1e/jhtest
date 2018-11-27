package hu.bme.dipterv.client.wicket.extension;

public class NavigateMapping {
	
	private String panelHolder;
	
	private NavigablePanel panel;
	
	public NavigateMapping(String panelHolder, NavigablePanel panel) {
		this.panelHolder = panelHolder;
		this.panel = panel;
	}
	
	public String getPanelHolder() {
		return panelHolder;
	}

	public void setPanelHolder(String panelHolder) {
		this.panelHolder = panelHolder;
	}

	public NavigablePanel getPanel() {
		return panel;
	}

	public void setPanel(NavigablePanel panel) {
		this.panel = panel;
	}
}