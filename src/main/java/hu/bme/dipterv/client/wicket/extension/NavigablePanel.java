package hu.bme.dipterv.client.wicket.extension;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.markup.html.panel.Panel;

public abstract class NavigablePanel extends Panel {

	/**
	 * NavigateAction és a holder-ek közötti mapping.
	 * Amelyik holder egy NaviagetAction-t map-el, a NaviagateAction id-ja bekerül
	 * ebbe a map-be.
	 */
	protected Map<Long, String> navigateActionIdMap = new HashMap<Long, String>();
	
	public NavigablePanel(String id) {
		super(id);
	}

	/**
	 * Naivgáláskor megnézi a panel, hogy van-e rajta ehhez az akcióhoz tartozó mapping.
	 * Ha van, akkor példányosítja az action-ben megadott osztályt a mapping-ben található holderrel.
	 * 
	 * @param navigateAction
	 * @throws Exception
	 */
	public void navigate(NavigateAction navigateAction) throws Exception {
		if(navigateActionIdMap.containsKey(navigateAction.getId())) {
			NavigablePanel panel = navigateAction.getNavigateTo().getDeclaredConstructor(String.class).newInstance(navigateActionIdMap.get(navigateAction.getId()));
			addOrReplace(panel);
		} else {
			((NavigablePanel) getParent()).navigate(navigateAction);
		}
	}
}