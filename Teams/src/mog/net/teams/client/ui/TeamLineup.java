package mog.net.teams.client.ui;

import java.util.List;

import mog.net.teams.client.DataService;
import mog.net.teams.client.DataServiceAsync;
import mog.net.teams.client.Player;
import mog.net.teams.client.event.SavePlayerCompleteEvent;
import mog.net.teams.client.event.SavePlayerCompleteEventHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class TeamLineup extends Composite {

	private static TeamLineupUiBinder uiBinder = GWT
			.create(TeamLineupUiBinder.class);

	interface TeamLineupUiBinder extends UiBinder<Widget, TeamLineup> {
	}
	
	private static DataServiceAsync dataService = GWT.create(DataService.class);
	
	@UiField FlowPanel teamTable;

	public TeamLineup(EventBus eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
		drawPlayers();
		
		eventBus.addHandler(SavePlayerCompleteEvent.TYPE, new SavePlayerCompleteEventHandler() {
			@Override
			public void onSavePlayerComplete() {
				drawPlayers();
			}
		});
		
	}
	
	// Non-refreshing panel
	public TeamLineup() {
		initWidget(uiBinder.createAndBindUi(this));
		drawPlayers();
	}
	
	public void drawPlayers() {
		dataService.getPlayers(new AsyncCallback<List<Player>>() {
			
			@Override
			public void onSuccess(List<Player> result) {
				teamTable.clear();
				for (Player p : result) {
					teamTable.add(new Image("/serveBlob?key=" + p.getImageKey()));
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}


}
