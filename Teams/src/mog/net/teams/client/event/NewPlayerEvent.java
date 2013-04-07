package mog.net.teams.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class NewPlayerEvent extends GwtEvent<NewPlayerEventHandler> {

	public static final Type<NewPlayerEventHandler> TYPE = new Type<NewPlayerEventHandler>();
	
	@Override
	public Type<NewPlayerEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(NewPlayerEventHandler handler) {
		handler.onNewPlayer();
	}

}
