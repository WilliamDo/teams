package mog.net.teams.client;

import mog.net.teams.client.event.ChangeViewEvent;
import mog.net.teams.client.event.ChangeViewEventHandler;
import mog.net.teams.client.event.ChangeViewEvent.View;
import mog.net.teams.client.ui.HomeLayout;
import mog.net.teams.client.ui.MatchListView;
import mog.net.teams.client.ui.NewMatch;
import mog.net.teams.client.ui.NewPlayer;
import mog.net.teams.client.ui.PlayerLayout;
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
		
		
		final RootLayoutPanel rootLayoutPanel = RootLayoutPanel.get();
		final PlayerLayout playerLayoutView = new PlayerLayout(eventBus);
		rootLayoutPanel.add(playerLayoutView);
		
		final MatchListView matchListView = new MatchListView(eventBus);
		rootLayoutPanel.add(matchListView);
		rootLayoutPanel.setWidgetVisible(matchListView, false);
		
		eventBus.addHandler(ChangeViewEvent.TYPE, new ChangeViewEventHandler() {
			
			@Override
			public void onChangeView(ChangeViewEvent changeViewEvent) {
				if (changeViewEvent.getTargetView() == View.MATCHES) {
					rootLayoutPanel.setWidgetVisible(playerLayoutView, false);
					rootLayoutPanel.setWidgetVisible(matchListView, true);
				} else if (changeViewEvent.getTargetView() == View.PLAYERS) {
					rootLayoutPanel.setWidgetVisible(playerLayoutView, true);
					rootLayoutPanel.setWidgetVisible(matchListView, false);
				}
			}
		});
		
		
		
	}

}
