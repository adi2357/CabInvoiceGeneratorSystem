package com.bridgelabz.cabinvoicegenerator;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CabInvoiceServiceTest {
	CabInvoiceGenerator invoiceGenerator = null;

	@Before
	public void initialize() {
		invoiceGenerator = new CabInvoiceGenerator();
	}

	@Test
	public void givenDistanceAndTime_ShouldReturnTotalFare() {
		double distance = 5.0;
		int time = 10;
		double fare = invoiceGenerator.calculateFare(distance, time);
		Assert.assertEquals(60.0, fare, 0.0);
	}

	@Test
	public void givenDistanceAndTime_WhenFareLessThanMinimumFare_ShouldReturnMinimumFare() {
		double distance = 0.3;
		int time = 1;
		double fare = invoiceGenerator.calculateFare(distance, time);
		Assert.assertEquals(5.0, fare, 0.0);
	}

	@Test
	public void givenMultipleRides_ShouldReturnTotalFare() {
		Ride[] rides = {
				new Ride(5.0, 10, "normal"),
				new Ride(0.3, 1, "normal"),
				new Ride(1.0, 5, "normal")
		};
		try {
			double totalFare = invoiceGenerator.calculateFare(rides);
			Assert.assertEquals(80.0, totalFare, 0.0);
		} catch (InvoiceException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void givenMultipleRides_ShouldReturnInvoiceSummary() {
		Ride[] rides = {
				new Ride(5.0, 10, "normal"),
				new Ride(0.3, 1, "normal"),
				new Ride(1.0, 5, "normal")
		};
		try {
			EnhancedInvoice invoiceSummary = invoiceGenerator.getInvoiceSummary(rides);
			EnhancedInvoice expectedInvoiceSummary = new EnhancedInvoice(3, 80.0);
			Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
		} catch (InvoiceException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void givenUserId_ShouldReturnInvoiceSummary() {
		RideRepository[] repositoryList = {new RideRepository(101, new Ride[]{new Ride(5.0, 10, "normal"), new Ride(0.3, 1, "normal"), new Ride(1.0, 5, "normal")}),
										   new RideRepository(102, new Ride[]{new Ride(5.5, 10, "normal"), new Ride(0.2, 2, "normal"), new Ride(3.0, 7, "normal")}),
										   new RideRepository(103, new Ride[]{new Ride(6.0, 10, "normal"), new Ride(0.1, 3, "normal"), new Ride(5.0, 10, "normal")})
										   };
		InvoiceService invoiceService = new InvoiceService(Arrays.asList(repositoryList));
		EnhancedInvoice invoiceSummary = invoiceService.getInvoice(101);
		EnhancedInvoice expectedInvoiceSummary = new EnhancedInvoice(3, 80.0);
		Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
	}

	@Test
	public void givenDistanceAndTime_WhenPremiumRides_ShouldReturnTotalFare() {
		double distance = 5.0;
		int time = 10;
		double fare = invoiceGenerator.calculatePremiumRideFare(distance, time);
		Assert.assertEquals(95.0, fare, 0.0);
	}

	@Test
	public void givenDistanceAndTimeForPremiumRide_WhenFareLessThanMinimumFare_ShouldReturnMinimumFare() {
		double distance = 1.0;
		int time = 1;
		double fare = invoiceGenerator.calculatePremiumRideFare(distance, time);
		Assert.assertEquals(20.0, fare, 0.0);
	}

	@Test
	public void givenMultipleRides_WhenRidesArePremium_ShouldReturnTotalPremiumRideFare() {
		Ride[] rides = {
				new Ride(5.0, 10, "premium"),
				new Ride(1.0, 1, "premium"),
				new Ride(1.0, 5, "premium")
		};
		try {
			double totalFare = invoiceGenerator.calculateFare(rides);
			Assert.assertEquals(140.0, totalFare, 0.0);
		} catch (InvoiceException e) {
			System.out.println(e.getMessage());
		}
	}
}
