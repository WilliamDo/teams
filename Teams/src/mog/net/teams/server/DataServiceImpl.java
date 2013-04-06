package mog.net.teams.server;

import static mog.net.teams.server.OfyService.ofy;
import static com.google.appengine.api.images.ServingUrlOptions.Builder.*;

import java.util.ArrayList;
import java.util.List;

import mog.net.teams.client.DataService;
import mog.net.teams.client.Player;
import mog.net.teams.shared.MatchWrapper;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Ref;

public class DataServiceImpl extends RemoteServiceServlet implements DataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 843808661668159681L;

	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	private ImagesService imagesService = ImagesServiceFactory.getImagesService();
	
	@Override
	public String getBlobstoreServiceAction() {
		// TODO I don't like this hard coded string here
		return blobstoreService.createUploadUrl("/uploadPlayer");
	}

	@Override
	public List<Player> getPlayers() {
		ArrayList<Player> players = new ArrayList<Player>(ofy().load().type(Player.class).list());
		
		for (Player p : players) {
			String servingUrl = imagesService.getServingUrl(withBlobKey(new BlobKey(p.getImageKey())));
			p.setImageServingUrl(servingUrl);
		}
		
		
		return players;
	}

	@Override
	public List<MatchWrapper> getMatches() {
		ArrayList<MatchWrapper> matches = new ArrayList<MatchWrapper>();
		List<Match> savedMatches = ofy().load().type(Match.class).list();
		for (Match match : savedMatches) {
			MatchWrapper matchWrapper = new MatchWrapper();
			matchWrapper.setTeam(match.getTeam());
			matchWrapper.setDate(match.getDate());
			matchWrapper.setId(match.getId());
			
			matchWrapper.setPlayer1(match.getPlayer1());
			matchWrapper.setPlayer2(match.getPlayer2());
			matchWrapper.setPlayer3(match.getPlayer3());
			
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

	@Override
	public void saveMatchPlayer(int order, long matchId, long playerId) {
		Player p = ofy().load().type(Player.class).id(playerId).get();
		
		Match m = ofy().load().type(Match.class).id(matchId).get();
		switch (order) {
		case 1: m.setPlayer1(p); break;
		case 2: m.setPlayer2(p); break;
		case 3: m.setPlayer3(p); break;
		}
		
		ofy().save().entity(m);
		
	}

	@Override
	public Player getPlayer(long playerId) {
		Player player = ofy().load().type(Player.class).id(playerId).get();
		String servingUrl = imagesService.getServingUrl(withBlobKey(new BlobKey(player.getImageKey())));
		player.setImageServingUrl(servingUrl);
		return player;
	}

	@Override
	public void savePlayer(Player p) {
		ofy().save().entity(p).now();
		
	}

	@Override
	public void deletePlayer(long playerId) {
		Ref<Player> player = ofy().load().type(Player.class).id(playerId);
		blobstoreService.delete(new BlobKey(player.get().getImageKey()));
		ofy().delete().entity(player.get()).now();
	}

}
