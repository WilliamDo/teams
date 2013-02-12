package mog.net.teams.client.ui;

import mog.net.teams.shared.MatchWrapper;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class MatchDetails extends Composite {

	private static MatchDetailsUiBinder uiBinder = GWT
			.create(MatchDetailsUiBinder.class);

	interface MatchDetailsUiBinder extends UiBinder<Widget, MatchDetails> {
	}
	
	@UiField Image imagePlayer1;
	@UiField Image imagePlayer2;
	@UiField Image imagePlayer3;
	
	@UiField SpanElement teamName;
	@UiField SpanElement dateSpan;

	public MatchDetails(MatchWrapper match) {
		initWidget(uiBinder.createAndBindUi(this));
		imagePlayer1.setUrl("/user.png");
		imagePlayer2.setUrl("/user.png");
		imagePlayer3.setUrl("/user.png");
		
		teamName.setInnerText(match.getTeam());
		dateSpan.setInnerText(match.getDate().toString());
	}
	
	@UiHandler("choosePlayer1")
	public void onChoosePlayer1(ClickEvent e) {
		PopupPanel popupPanel = new PopupPanel(true);
		popupPanel.add(new TeamLineup());
		popupPanel.show();
	}

}
