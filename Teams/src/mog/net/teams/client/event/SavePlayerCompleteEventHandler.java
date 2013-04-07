package mog.net.teams.client.event;

import com.google.gwt.event.shared.EventHandler;
import mog.net.teams.client.event.SavePlayerCompleteEvent.Action;

public interface SavePlayerCompleteEventHandler extends EventHandler {

	void onSavePlayerComplete(Action action);
	
}
