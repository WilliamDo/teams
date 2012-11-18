package mog.net.teams.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SavePlayerCompleteEvent extends GwtEvent<SavePlayerCompleteEventHandler> {

	public static Type<SavePlayerCompleteEventHandler> TYPE = new Type<SavePlayerCompleteEventHandler>();
	
	@Override
	public Type<SavePlayerCompleteEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SavePlayerCompleteEventHandler handler) {
		handler.onSavePlayerComplete();
	}

}
