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
			String navigateMapping = navigateActionIdMap.get(navigateAction.getId());
			replace(navigateAction.getNavigateTo().getDeclaredConstructor(String.class).newInstance(navigateMapping));
		} else {
			((NavigablePanel) getParent()).navigate(navigateAction);
		}
	}
	
	
	/*public void navigate(NavigateAction navigateAction) throws Exception {
		if(navigateActionIdMap.containsKey(navigateAction.getId())) {
			NavigablePanel panel = navigateAction.getNavigateTo().getDeclaredConstructor(String.class).newInstance(navigateActionIdMap.get(navigateAction.getId()));
			addOrReplace(panel);
		} else {
			((NavigablePanel) getParent()).navigate(navigateAction);
		}
	}*/
	
	/**
	 * Ezek kellenek a visible állítgatós módszerhez
	 */
	/*protected Map<Long, NavigateMapping> navigateActionIdMap = new HashMap<Long, NavigateMapping>();
	protected Map<String, List<NavigablePanel>> holderToPanels = new HashMap<String, List<NavigablePanel>>();*/
	
	//public void navigate(NavigateAction navigateAction) throws Exception {
		//if(navigateActionIdMap.containsKey(navigateAction.getId())) {
			//NavigateMapping navigateMapping = navigateActionIdMap.get(navigateAction.getId());
			
			//String navigateMapping = navigateActionIdMap.get(navigateAction.getId());
			//replace(navigateAction.getNavigateTo().getDeclaredConstructor(String.class).newInstance(navigateMapping));
			//replace(navigateMapping.getPanel().getClass().getDeclaredConstructor(String.class).newInstance(navigateMapping.getPanelHolder()));
			
			/**
			 * ÍGY IS LEHET.
			 * List<NavigablePanel> panels = holderToPanels.get(navigateMapping.getPanelHolder());
			 */
			/*for(NavigablePanel panel : panels) {
				//panel.setVisible((panel.equals(navigateMapping.getPanel()) ? true : false));
				if(panel.equals(navigateMapping.getPanel())) {
					replace(panel);
				}
			}*/
			
		//} else {
			//((NavigablePanel) getParent()).navigate(navigateAction);
		//}
	//}
	
	/*public void addPanelToHolder(String holder, NavigablePanel panel) {
		if(holderToPanels.containsKey(holder)) {
			holderToPanels.get(holder).add(panel);
		} else {
			List<NavigablePanel> panels = new ArrayList<NavigablePanel>();
			panels.add(panel);
			holderToPanels.put(holder, panels);
		}
	}*/
}