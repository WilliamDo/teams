package mog.net.teams.client.ui;

import mog.net.teams.shared.MatchWrapper;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;
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
	
	private final Long matchId;

	public MatchDetails(MatchWrapper match) {
		initWidget(uiBinder.createAndBindUi(this));
		
		if (match.getPlayer1() != null) {
			imagePlayer1.setUrl("/serveBlob?key=" + match.getPlayer1().getImageKey());
		} else {
			imagePlayer1.setUrl("/user.png");
		}
		
		if (match.getPlayer2() != null) {
			imagePlayer2.setUrl("/serveBlob?key=" + match.getPlayer2().getImageKey());
		} else {
			imagePlayer2.setUrl("/user.png");
		}
		
		if (match.getPlayer3() != null) {
			imagePlayer3.setUrl("/serveBlob?key=" + match.getPlayer3().getImageKey());
		} else {
			imagePlayer3.setUrl("/user.png");
		}
		
		
		this.matchId = match.getId();
		
		teamName.setInnerText(match.getTeam());
		dateSpan.setInnerText(match.getDate().toString());
	}
	
	@UiHandler("choosePlayer1")
	public void onChoosePlayer1(ClickEvent e) {
		showPopup(1);
	}
	
	@UiHandler("choosePlayer2")
	public void onChoosePlayer2(ClickEvent e) {
		showPopup(2);
	}
	
	@UiHandler("choosePlayer3")
	public void onChoosePlayer3(ClickEvent e) {
		showPopup(3);
	}
	
	private void showPopup(int order) {
		final PopupPanel popupPanel = new PopupPanel(true);
		popupPanel.add(new TeamLineup(this.matchId, order));
		popupPanel.setPopupPositionAndShow(new PositionCallback() {
			
			@Override
			public void setPosition(int offsetWidth, int offsetHeight) {
				int left = (Window.getClientWidth() - offsetWidth) / 2;
				int top = (Window.getClientHeight() - offsetHeight) / 2;
				popupPanel.setPopupPosition(left, top);
				
			}
		});
	}

}
