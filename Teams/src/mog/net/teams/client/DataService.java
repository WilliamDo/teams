package mog.net.teams.client;

import java.util.List;

import mog.net.teams.shared.MatchWrapper;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("data")
public interface DataService extends RemoteService {
	
	public String getBlobstoreServiceAction();
	
	public List<Player> getPlayers();
	
	public Player getPlayer(long playerId);
	
	public List<MatchWrapper> getMatches();
	
	public void saveMatch(MatchWrapper newMatch);
	
	public void saveMatchPlayer(int order, long matchId, long playerId);

}
