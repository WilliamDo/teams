package mog.net.teams.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Teams implements EntryPoint {

	private final TeamsGinjector injector = GWT.create(TeamsGinjector.class);
	
	@Override
	public void onModuleLoad() {
		EventBus eventBus = injector.getEventBus();
		
		NewPlayer newPlayerWidget = new NewPlayer(eventBus);
		TeamLineup teamWidget = new TeamLineup(eventBus);
		
		HomeLayout homeLayout = new HomeLayout(eventBus, teamWidget, newPlayerWidget);
		
		
		RootLayoutPanel.get().add(homeLayout);
	}

}
