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
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class PlayerLayout extends Composite {

	private static PlayerLayoutUiBinder uiBinder = GWT
			.create(PlayerLayoutUiBinder.class);

	interface PlayerLayoutUiBinder extends UiBinder<Widget, PlayerLayout> {
	}
	
	private static final DataServiceAsync dataService = GWT.create(DataService.class);
	
	@UiField FlowPanel playersFlowPanel;
	@UiField SimplePanel newPlayerPanel;
	
	private EventBus eventBus;

	public PlayerLayout(EventBus eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
		drawPlayers();
		newPlayerPanel.add(new NewPlayer(eventBus));
		this.eventBus = eventBus;
		
		eventBus.addHandler(SavePlayerCompleteEvent.TYPE, new SavePlayerCompleteEventHandler() {
			
			@Override
			public void onSavePlayerComplete() {
				drawPlayers();
				
			}
		});
	}
	
	public void drawPlayers() {
		
		dataService.getPlayers(new AsyncCallback<List<Player>>() {

			@Override
			public void onSuccess(List<Player> result) {
				playersFlowPanel.clear();
				
				for (Player p : result) {
					playersFlowPanel.add(new PlayerWidget(p, eventBus));
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
