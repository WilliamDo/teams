package mog.net.teams.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class LoadPlayerEvent extends GwtEvent<LoadPlayerEventHandler> {

	public static final Type<LoadPlayerEventHandler> TYPE = new Type<LoadPlayerEventHandler>();

	private final long id;
	
	public LoadPlayerEvent(long id) {
		this.id = id;
	}
	
	@Override
	public Type<LoadPlayerEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LoadPlayerEventHandler handler) {
		handler.onLoadPlayer(this);
	}
	
	public long getId() {
		return this.id;
	}

}
