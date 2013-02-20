package mog.net.teams.shared;

import java.io.Serializable;
import java.util.Date;

import mog.net.teams.client.Player;

public class MatchWrapper implements Serializable {

	private Player player1;
	private Player player2;
	private Player player3;
	
	private String team;
	private Date date;
	private Long id;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public MatchWrapper(String team, Player p1, Player p2, Player p3) {
		this.team = team;
		this.player1 = p1;
		this.player2 = p2;
		this.player3 = p3;
		
	}
	
	public MatchWrapper() {
		// Required by GWT serialisation
	}
	
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public void setPlayer3(Player player3) {
		this.player3 = player3;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Player getPlayer1() {
		return this.player1;
	}
	
	public Player getPlayer2() {
		return this.player2;
	}
	
	public Player getPlayer3() {
		return this.player3;
	}
	
	public String getTeam() {
		return this.team;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
