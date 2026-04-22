package mediaRentalManager;

public class Media implements Comparable<Media> {
	
	protected String title;
	protected int copiesAvail;
	
	public String toString() {
		
		return "Title: " + title + ", Copies Available: " + copiesAvail;
		
	}
	
	public int getCopiesAvail() {
		return copiesAvail;
	}
	
	public String getTitle() {
		return title;
	}

	@Override
	public int compareTo(Media o) {
		return this.title.compareTo(o.title);
	}
}
