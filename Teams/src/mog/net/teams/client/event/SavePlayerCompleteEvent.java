package mog.net.teams.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SavePlayerCompleteEvent extends GwtEvent<SavePlayerCompleteEventHandler> {

	public static Type<SavePlayerCompleteEventHandler> TYPE = new Type<SavePlayerCompleteEventHandler>();
	
	public enum Action {
		MODIFIED, UNCHANGED
	}
	
	private final Action action;
	
	public SavePlayerCompleteEvent(Action action) {
		this.action = action;
	}
	
	public static Type<SavePlayerCompleteEventHandler> getTYPE() {
		return TYPE;
	}

	public Action getAction() {
		return action;
	}

	@Override
	public Type<SavePlayerCompleteEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SavePlayerCompleteEventHandler handler) {
		handler.onSavePlayerComplete(action);
	}

}
