package mog.net.teams.client.ui;

import java.util.List;

import mog.net.teams.client.DataService;
import mog.net.teams.client.DataServiceAsync;
import mog.net.teams.client.Player;
import mog.net.teams.client.event.SavePlayerCompleteEvent;
import mog.net.teams.client.event.SavePlayerCompleteEvent.Action;
import mog.net.teams.client.event.SavePlayerCompleteEventHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
	
	private static final DataServiceAsync dataService = GWT.create(DataService.class);
	
	private final Long matchId;
	private final int order;
	
	@UiField FlowPanel teamTable;

	public TeamLineup(EventBus eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
		drawPlayers();
		
		this.matchId = null;
		this.order = -1; // Error
		
		eventBus.addHandler(SavePlayerCompleteEvent.TYPE, new SavePlayerCompleteEventHandler() {
			@Override
			public void onSavePlayerComplete(Action action) {
				drawPlayers();
			}
		});
		
	}
	
	// Non-refreshing panel for use by selecting player
	public TeamLineup(Long matchId, int playerOrder) {
		initWidget(uiBinder.createAndBindUi(this));
		this.matchId = matchId;
		this.order = playerOrder;
		drawPlayers();
	}
	
	public void drawPlayers() {
		dataService.getPlayers(new AsyncCallback<List<Player>>() {
			
			@Override
			public void onSuccess(List<Player> result) {
				teamTable.clear();
				for (Player p : result) {
					final Long playerId = p.getId();
					Image image = new Image("/serveBlob?key=" + p.getImageKey());
					image.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							saveMatchPlayer(matchId, order, playerId);
						}
					});
					teamTable.add(image);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void saveMatchPlayer(long matchId, int order, long playerId) {
		dataService.saveMatchPlayer(order, matchId, playerId, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
		});
	}


}
