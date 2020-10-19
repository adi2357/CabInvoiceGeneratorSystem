package com.bridgelabz.cabinvoicegenerator;

public class CabInvoiceGenerator {
	private static final double COST_PER_KILOMETER = 10.0;
	private static final int COST_PER_MINUTE = 1;
	private static final double MINIMUM_RIDE_FARE = 5.0;
	private static final double PREMIUM_RIDE_COST_PER_KILOMETER = 15.0;
	private static final int PREMIUM_RIDE_COST_PER_MINUTE = 2;
	private static final double MINIMUM_PREMIUM_RIDE_FARE = 20.0;
	

	public double calculateFare(double distance, int time) {
		double totalFare = distance * COST_PER_KILOMETER + time * COST_PER_MINUTE;
		return Math.max(MINIMUM_RIDE_FARE, totalFare);
	}
	
	public double calculatePremiumRideFare(double distance, int time) {
		double totalFare = distance * PREMIUM_RIDE_COST_PER_KILOMETER + time * PREMIUM_RIDE_COST_PER_MINUTE;
		return Math.max(MINIMUM_PREMIUM_RIDE_FARE, totalFare);
	}

	public double calculateFare(Ride[] rides) throws InvoiceException{
		double totalFareForAllRides = 0.0;
		for (Ride ride : rides) {
			if(ride.category.equals("normal"))
				totalFareForAllRides += calculateFare(ride.distance, ride.time);
			else if(ride.category.equals("premium"))
				totalFareForAllRides += calculatePremiumRideFare(ride.distance, ride.time);
			else throw new InvoiceException("Invalid ride category", InvoiceException.ExceptionType.INVALID_RIDE_TYPE);
		}
		return totalFareForAllRides;
	}

	public EnhancedInvoice getInvoiceSummary(Ride[] rides) throws InvoiceException {
		double totalFare = calculateFare(rides);
		return new EnhancedInvoice(rides.length, totalFare);
	}
}
