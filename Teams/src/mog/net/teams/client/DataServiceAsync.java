package mog.net.teams.client;

import java.util.List;


import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataServiceAsync {
	
	void getBlobstoreServiceAction(AsyncCallback<String> callback);
	
	void getPlayers(AsyncCallback<List<Player>> callback);

}
