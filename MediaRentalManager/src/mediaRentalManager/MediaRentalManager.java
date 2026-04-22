package mediaRentalManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MediaRentalManager implements MediaRentalManagerInt {
	
	private List<Media> media = new ArrayList<Media>(); 
	protected List<Customer> customers = new ArrayList<Customer>();
	private int maxRentals = 2;
	

	@Override
	public void addCustomer(String name, String address, String plan) {
		
		Customer c = new Customer(name, address, plan);

		customers.add(c);
	}

	@Override
	public void addMovie(String title, int copiesAvailable, String rating) {
		
		Movie m = new Movie(title, copiesAvailable, rating);

		media.add(m);
	}

	@Override
	public void addAlbum(String title, int copiesAvailable, String artist, String songs) {

		Album a = new Album(title, copiesAvailable, artist, songs);
		
		media.add(a);		
	}

	@Override
	public void setLimitedPlanLimit(int value) {
		
		this.maxRentals = value;
		}

	@Override
	public String getAllCustomersInfo() {
		
		Collections.sort(customers);
		
		String info = "***** Customers' Information *****\n";
		for (Customer c : customers) {
			info += c.toString();
		}
		
		return info;
	}

	@Override
	public String getAllMediaInfo() {
		
		Collections.sort(media);
		
		String info = "***** Media Information *****\n";
		for (Media m : media) {
			info += m.toString() + "\n";
		}
		
		return info;
		}

	@Override
	public boolean addToQueue(String customerName, String mediaTitle) {
		int customer = -1; 
	
		for (int i = 0; i < customers.size(); i ++) {
			if (customers.get(i).getName() == customerName) {
				customer = i;
			}
		}
		
		if (customer == -1 || customers.get(customer).getQueue().contains(mediaTitle)) {
			return false;
		}
		
		else {
			customers.get(customer).interested.add(mediaTitle);
			return true;
		}
	}

	@Override
	public boolean removeFromQueue(String customerName, String mediaTitle) {
	
		try {
			
			int customer = -1;
			
			for (int i = 0; i < customers.size(); i ++) {
				if (customers.get(i).getName() == customerName) {
					customer = i;
				}
			}
			
			customers.get(customer).interested.remove(mediaTitle);
			return true;
			
		}
		catch (Exception e) {
			return false;
	}
		
	}

	@Override
	public String processRequests() {
		String requests = "";
		Collections.sort(customers);
		
		for (Customer c : customers) {

				int size = c.interested.size();
				int queueNum = 0;
				if (c.getPlan() == "LIMITED") {
					for (int j = 0; j < size; j++) {
						for (int k = 0; k < media.size(); k++) {
							Media m = media.get(k);
							String mediaTitle = m.getTitle();
							if (mediaTitle.equals(c.interested.get(queueNum))) {
								if (m.getCopiesAvail() > 0) {
									if (c.rented.size() < maxRentals) {
										c.interested.remove(queueNum);
										c.rented.add(mediaTitle);
										m.copiesAvail--;
										requests += "Sending " + mediaTitle + " to " + c.getName() + "\n";
										break;
									}
								} else {
									queueNum++;
								}
							}
						}
					}
				} else if (c.getPlan() == "UNLIMITED") {
					for (int j = 0; j < size; j++) {
						for (int k = 0; k < media.size(); k++) {
							Media m = media.get(k);
							String mediaTitle = m.getTitle();
							if (mediaTitle.equals(c.interested.get(queueNum))) {
								if (m.getCopiesAvail() > 0) {
									c.interested.remove(queueNum);
									c.rented.add(mediaTitle);
									m.copiesAvail--;
									requests += "Sending " + mediaTitle + " to " + c.getName() + "\n";
									break;
								} else {
									queueNum++;
								}
							}
						}
					}
				}
			}
		
		return requests;
	}

	@Override
	public boolean returnMedia(String customerName, String mediaTitle) {
		
		int num = -1;
		
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getName() == customerName) {
				num = i;
			}
		}
			
			if (num == -1) {
				return false;
			}
			
			Customer c = customers.get(num);
			
			for (int j = 0; j < c.rented.size(); j++) {
				if (c.rented.get(j).equals(mediaTitle)) {
					c.rented.remove(mediaTitle);

			}
				for (int k = 0; k < media.size(); k++) {
					if (media.get(k).getTitle().equals(mediaTitle)) {
						media.get(k).copiesAvail++;
					}
				}
			}
						return true;
				}

	@Override
	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {
		
		ArrayList<String> output = new ArrayList<String>();
		
		boolean titleP = false;
		boolean ratingP = false;
		boolean artistP = false;
		boolean songsP = false;
		
		for (Media m : media) {
			
			titleP = title == null || m.getTitle().equals(title);
			
			if (m instanceof Movie) {
				Movie m2 = (Movie) m;
				ratingP = rating == null || m2.getRating().equals(rating);
				artistP = (artist == null);
				songsP = songs == null;
			}
			
			else if (m instanceof Album) {
				Album a = (Album) m;
				
				artistP = artist == null || a.getArtist().equals(artist);
				songsP = songs == null || a.getSongs().indexOf(songs) >= 0;
				ratingP = rating == null;
			}
			
			if (titleP && ratingP && artistP && songsP) {
				output.add(m.getTitle());
			}
		}
		
		Collections.sort(output);
		return output;
		}
}