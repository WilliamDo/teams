package mog.net.teams.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Teams implements EntryPoint {

	@Override
	public void onModuleLoad() {
		HomeLayout homeLayout = new HomeLayout();
		RootLayoutPanel.get().add(homeLayout);
		
	}

}
