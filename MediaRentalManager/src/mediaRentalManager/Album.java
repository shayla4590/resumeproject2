package mediaRentalManager;

public class Album extends Media {
	
	private String artist;
	private String songs;
	
	public Album(String title, int copiesAvail, String artist, String songs) {
		
		this.title = title;
		this.copiesAvail = copiesAvail;
		this.artist = artist;
		this.songs = songs;
	}

	public String toString() {
		
		return super.toString() + ", Artist: " + artist + ", Songs: " + songs;
		
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getSongs() {
		return songs;
	}

}
