package mog.net.teams.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Teams implements EntryPoint {

	@Override
	public void onModuleLoad() {
		HandlerManager eventBus = new HandlerManager(null);
		HomeLayout homeLayout = new HomeLayout(eventBus);
		RootLayoutPanel.get().add(homeLayout);
	}

}
