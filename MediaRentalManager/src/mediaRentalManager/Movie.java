package mediaRentalManager;

public class Movie extends Media {
	
	private String rating;
	
	public Movie(String title, int copiesAvail, String rating) {
		
		this.title = title;
		this.copiesAvail = copiesAvail;
		this.rating = rating;
	}
	
	public String toString() {
		
		return super.toString() + ", Rating: " + rating;
		
	}
	
	public String getRating() {
		return rating;
	}

}
