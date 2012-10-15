package mog.net.teams.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class HomeLayout extends Composite {

	private static HomeLayoutUiBinder uiBinder = GWT
			.create(HomeLayoutUiBinder.class);

	interface HomeLayoutUiBinder extends UiBinder<Widget, HomeLayout> {
	}
	
	@UiField ScrollPanel centerScrollPanel;

	public HomeLayout() {
		initWidget(uiBinder.createAndBindUi(this));
		centerScrollPanel.setWidget(new Label("Testing"));
	}

}
