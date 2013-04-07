package mog.net.teams.client;

import java.util.List;

import mog.net.teams.shared.MatchWrapper;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataServiceAsync {
	
	void getBlobstoreServiceAction(AsyncCallback<String> callback);
	
	void getPlayers(AsyncCallback<List<Player>> callback);
	
	void getMatches(AsyncCallback<List<MatchWrapper>> callback);
	
	void saveMatch(MatchWrapper match, AsyncCallback<Void> callback);
	
	void saveMatchPlayer(int order, long matchId, long playerId, AsyncCallback<Void> callback);
	
	void getPlayer(long playerId, AsyncCallback<Player> callback);
	
	void savePlayer(Player p, AsyncCallback<Void> callback);
	
	void deletePlayer(long playerId, AsyncCallback<Void> callback);
	
	void getImageServingUrl(String blobKey, AsyncCallback<String> callback);

}
