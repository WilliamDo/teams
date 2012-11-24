package mog.net.teams.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(TeamsClientModule.class)
public interface TeamsGinjector extends Ginjector {

	public EventBus getEventBus();
	
}
