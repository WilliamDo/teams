package mog.net.teams.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class TeamsClientModule extends AbstractGinModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
	}

}
