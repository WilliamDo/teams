package mog.net.teams.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ChangeViewEvent extends GwtEvent<ChangeViewEventHandler> {
	
	public static enum View {
		PLAYERS, MATCHES
	}
	
	private final View targetView;

	public static final Type<ChangeViewEventHandler> TYPE = new Type<ChangeViewEventHandler>();
	
	public ChangeViewEvent(View targetView) {
		this.targetView = targetView;
	}
	
	@Override
	public Type<ChangeViewEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(ChangeViewEventHandler handler) {
		handler.onChangeView(this);
		
	}
	
	public View getTargetView() {
		return targetView;
	}
	
	
	

}
