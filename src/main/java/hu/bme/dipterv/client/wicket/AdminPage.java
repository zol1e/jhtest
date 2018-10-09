package hu.bme.dipterv.client.wicket;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;

import hu.bme.dipterv.security.AuthoritiesConstants;

@AuthorizeInstantiation(AuthoritiesConstants.ADMIN)
public class AdminPage extends WebPage
{
	
}