package hu.bme.dipterv.client.wicket;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;

@AuthorizeInstantiation(Roles.ADMIN)
public class AdminPage extends WebPage
{
	
}