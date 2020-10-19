package com.bridgelabz.cabinvoicegenerator;

public class Ride {
	public double distance;
	public int time;
	public String category = "normal";

	public Ride(double distance, int time, String category) {
		this.distance = distance;
		this.time = time;
		this.category = category.toLowerCase();
	}
}
