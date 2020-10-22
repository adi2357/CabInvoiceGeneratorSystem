package com.bridgelabz.cabinvoicegenerator;

import java.util.List;

public class CabInvoiceGenerator {
	public double calculateFare(double distance, int time) {
		return CabRide.NORMAL.calculateFarePerRide(distance, time);
	}
	
	public double calculatePremiumRideFare(double distance, int time) {
		return CabRide.PREMIUM.calculateFarePerRide(distance, time);
	}

	public double calculateFare(List<Ride> rides) throws InvoiceException{
		if(rides == null) {
			throw new InvoiceException("Incorrect user id", InvoiceException.ExceptionType.NO_SUCH_USER);
		}
		double totalFareForAllRides = 0.0;
		for (Ride ride : rides) {
			if(ride.rideType == CabRide.NORMAL || ride.rideType == CabRide.PREMIUM)
				totalFareForAllRides += ride.rideType.calculateFarePerRide(ride);
			else throw new InvoiceException("Invalid ride category", InvoiceException.ExceptionType.INVALID_RIDE_TYPE);
		}
		return totalFareForAllRides;
	}
}
