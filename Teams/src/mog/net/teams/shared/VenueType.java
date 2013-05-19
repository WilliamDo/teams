package mog.net.teams.shared;

public enum VenueType {
	
	HOME("H"), AWAY("A");

	String shortName;
	
	VenueType(String shortName) {
		this.shortName = shortName;
	}
	
	public String getShortName() {
		return this.shortName;
	}
	
	
}
