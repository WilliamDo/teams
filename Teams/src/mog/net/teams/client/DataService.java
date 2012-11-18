package mog.net.teams.client;

import java.util.List;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("data")
public interface DataService extends RemoteService {
	
	public String getBlobstoreServiceAction();
	
	public List<Player> getPlayers();

}
