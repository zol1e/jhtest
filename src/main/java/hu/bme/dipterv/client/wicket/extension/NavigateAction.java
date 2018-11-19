package hu.bme.dipterv.client.wicket.extension;

public class NavigateAction {

	private Class<? extends NavigablePanel> navigateTo;
	
	private Long id;
	
	public NavigateAction(Class<? extends NavigablePanel> navigateTo, Long id) {
		this.navigateTo = navigateTo;
		this.id = id;
	}
	
	public Class<? extends NavigablePanel> getNavigateTo() {
		return navigateTo;
	}

	public Long getId() {
		return id;
	}
}
