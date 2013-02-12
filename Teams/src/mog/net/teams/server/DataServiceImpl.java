package mog.net.teams.server;

import static mog.net.teams.server.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import mog.net.teams.client.DataService;
import mog.net.teams.client.Player;
import mog.net.teams.shared.MatchWrapper;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DataServiceImpl extends RemoteServiceServlet implements DataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 843808661668159681L;

	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	@Override
	public String getBlobstoreServiceAction() {
		// TODO I don't like this hard coded string here
		return blobstoreService.createUploadUrl("/uploadPlayer");
	}

	@Override
	public List<Player> getPlayers() {
		return new ArrayList<Player>(ofy().load().type(Player.class).list());
	}

	@Override
	public List<MatchWrapper> getMatches() {
		ArrayList<MatchWrapper> matches = new ArrayList<MatchWrapper>();
		List<Match> savedMatches = ofy().load().type(Match.class).list();
		for (Match match : savedMatches) {
			MatchWrapper matchWrapper = new MatchWrapper();
			matchWrapper.setTeam(match.getTeam());
			matchWrapper.setDate(match.getDate());
			matches.add(matchWrapper);
		}
		
		return matches;
				
	}

	@Override
	public void saveMatch(MatchWrapper newMatch) {
		Match match = new Match();
		match.setTeam(newMatch.getTeam());
		match.setDate(newMatch.getDate());
		ofy().save().entity(match);
		
	}

}
