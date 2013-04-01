package mog.net.teams.client.ui;

import mog.net.teams.client.Player;
import mog.net.teams.client.event.LoadPlayerEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PlayerWidget extends Composite {

	private static PlayerWidgetUiBinder uiBinder = GWT
			.create(PlayerWidgetUiBinder.class);

	interface PlayerWidgetUiBinder extends UiBinder<Widget, PlayerWidget> {
	}
	
	@UiField Image image;
	@UiField Label name;

	public PlayerWidget(final Player player, final EventBus eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
		image.setUrl(player.getImageServingUrl() + "=s100");
		name.setText(player.getFirstName());
		
		image.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new LoadPlayerEvent(player.getId()));
			}
			
		});
	}

}
