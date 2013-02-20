package mog.net.teams.server;

import java.io.Serializable;
import java.util.Date;

import mog.net.teams.client.Player;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Match implements Serializable {

	@Id Long id;
	@Load Ref<Player> player1;
	@Load Ref<Player> player2;
	@Load Ref<Player> player3;
	
	String team;
	Date date;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Player getPlayer1() {
		return player1 != null ? player1.get() : null;
	}
	
	public Player getPlayer2() {
		return player2 != null ? player2.get() : null;
	}
	
	public Player getPlayer3() {
		return player3 != null ? player3.get() : null;
	}
	
	public void setPlayer1(Player player) {
		player1 = Ref.create(player);
	}
	
	public void setPlayer2(Player player) {
		player2 = Ref.create(player);
	}
	
	public void setPlayer3(Player player) {
		player3 = Ref.create(player);
	}

	public String getTeam() {
		return this.team;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}
	
}
