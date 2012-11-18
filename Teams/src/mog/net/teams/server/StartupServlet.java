package mog.net.teams.server;

import javax.servlet.http.HttpServlet;

import mog.net.teams.client.Player;

import com.googlecode.objectify.ObjectifyService;

public class StartupServlet extends HttpServlet {

	static {
		ObjectifyService.register(Player.class);
	}
	
}
