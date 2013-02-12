package mog.net.teams.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SaveMatchCompleteEvent extends GwtEvent<SaveMatchCompleteEventHandler> {

	public static Type<SaveMatchCompleteEventHandler> TYPE = new Type<SaveMatchCompleteEventHandler>();
	
	@Override
	public Type<SaveMatchCompleteEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SaveMatchCompleteEventHandler handler) {
		handler.onSaveMatchComplete();
	}

}
