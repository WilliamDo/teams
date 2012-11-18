package mog.net.teams.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class HomeLayout extends Composite {

	private static HomeLayoutUiBinder uiBinder = GWT
			.create(HomeLayoutUiBinder.class);

	interface HomeLayoutUiBinder extends UiBinder<Widget, HomeLayout> {
	}
	
	@UiField ScrollPanel centerScrollPanel;
	
	@UiField SimplePanel southPanel;
	
	public HomeLayout(HandlerManager eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
		southPanel.setWidget(new TeamLineup(eventBus));
		centerScrollPanel.setWidget(new NewPlayer(eventBus));
		
	}

}
