package mog.net.teams.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
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
	
	public HomeLayout(EventBus eventBus, Widget southWidget, Widget mainWidget) {
		initWidget(uiBinder.createAndBindUi(this));
		southPanel.setWidget(southWidget);
		centerScrollPanel.setWidget(mainWidget);
		
	}

}
