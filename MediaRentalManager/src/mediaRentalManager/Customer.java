package mediaRentalManager;

import java.util.ArrayList;

public class Customer implements Comparable<Customer> {
	
	private String name;
	private String address;
	private String plan;
	protected ArrayList<String> interested;
	protected ArrayList<String> rented;

	public Customer(String name, String address, String plan) {
		
		this.name = name;
		this.address = address;
		this.plan = plan;
		interested = new ArrayList<String>();
		rented = new ArrayList<String>();
		
	}

	public String toString() {

		return "Name: " + name + ", Address: " + address + ", Plan: " + plan + "\nRented: " + rented + "\nQueue: " + interested + "\n";
		}
	
	public ArrayList<String> getQueue() {
		return interested;
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getPlan() {
		return plan;
	}

	@Override
	public int compareTo(Customer o) {
	return this.name.compareTo(o.name);
	}
	
	}
