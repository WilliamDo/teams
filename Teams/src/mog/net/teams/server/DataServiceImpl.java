package mog.net.teams.server;

import static mog.net.teams.server.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import mog.net.teams.client.DataService;
import mog.net.teams.client.Player;

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

}
