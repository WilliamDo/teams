package mog.net.teams.client;

import mog.net.teams.client.ui.HomeLayout;
import mog.net.teams.client.ui.MatchListView;
import mog.net.teams.client.ui.NewMatch;
import mog.net.teams.client.ui.NewPlayer;
import mog.net.teams.client.ui.TeamLineup;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Teams implements EntryPoint {

	private final TeamsGinjector injector = GWT.create(TeamsGinjector.class);
	
	@Override
	public void onModuleLoad() {
		EventBus eventBus = injector.getEventBus();
		
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(new NewPlayer(eventBus));
		vPanel.add(new NewMatch(eventBus));
		vPanel.add(new MatchListView(eventBus));
		TeamLineup teamWidget = new TeamLineup(eventBus);
		
		HomeLayout homeLayout = new HomeLayout(eventBus, teamWidget, vPanel);
		
		
		RootLayoutPanel.get().add(homeLayout);
	}

}
