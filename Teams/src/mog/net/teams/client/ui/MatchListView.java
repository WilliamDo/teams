package mog.net.teams.client.ui;

import java.util.List;

import mog.net.teams.client.DataService;
import mog.net.teams.client.DataServiceAsync;
import mog.net.teams.client.event.ChangeViewEvent;
import mog.net.teams.client.event.ChangeViewEvent.View;
import mog.net.teams.client.event.SaveMatchCompleteEvent;
import mog.net.teams.client.event.SaveMatchCompleteEventHandler;
import mog.net.teams.shared.MatchWrapper;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MatchListView extends Composite {

	private static MatchListViewUiBinder uiBinder = GWT
			.create(MatchListViewUiBinder.class);
	
	
	private final DataServiceAsync dataService = GWT.create(DataService.class);

	interface MatchListViewUiBinder extends UiBinder<Widget, MatchListView> {
	}
	
	@UiField VerticalPanel matchesPanel;
	
	@UiField SimplePanel newMatchPanel;

	private final EventBus eventBus;

	public MatchListView(EventBus eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.eventBus = eventBus;
		
		newMatchPanel.add(new NewMatch(eventBus));
		
		drawMatches();
		
		eventBus.addHandler(SaveMatchCompleteEvent.TYPE, new SaveMatchCompleteEventHandler() {
			
			@Override
			public void onSaveMatchComplete() {
				drawMatches();
			}
		});
		
	}
	
	public void drawMatches() {
		matchesPanel.clear();
		
		dataService.getMatches(new AsyncCallback<List<MatchWrapper>>() {
			
			@Override
			public void onSuccess(List<MatchWrapper> result) {
				// TODO Auto-generated method stub
				for (MatchWrapper matchWrapper : result) {
					matchesPanel.add(new MatchDetails(matchWrapper));
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	@UiHandler("viewPlayersButton")
	void onViewPlayers(ClickEvent e) {
		this.eventBus.fireEvent(new ChangeViewEvent(View.PLAYERS));
	}

}
