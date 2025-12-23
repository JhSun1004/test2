package com;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	private static final String password = "123";
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	private double nerfFactor = 1.0; // 1.0 means no nerf, 0.5 means 50% nerf

	public int add(int a, int b) {
		try {
			if (a < 0 || b < 0) {
				throw new IllegalArgumentException("Both numbers must be non-negative.");
			}
			if (Integer.MAX_VALUE - a < b) {
				throw new ArithmeticException("Integer overflow when trying to add " + a + " + " + b);
			}
			int result = a + b;
			// Apply nerf factor if set
			if (nerfFactor < 1.0) {
				int originalResult = result;
				result = (int) (result * nerfFactor);
				logger.log(Level.INFO, "Nerfed result from {0} to {1} with factor {2}", new Object[]{originalResult, result, nerfFactor});
			}
			logger.log(Level.INFO, "Successfully added {0} and {1} to get {2}", new Object[]{a, b, result});
			return result;
		} catch (IllegalArgumentException | ArithmeticException e) {
			logger.log(Level.SEVERE, "Exception caught during addition: " + e.getMessage());
			throw e; // Re-throwing the exception to let the caller know an error occurred
		}
	}

	public void setNerfFactor(double factor) {
		if (factor < 0.0 || factor > 1.0) {
			throw new IllegalArgumentException("Nerf factor must be between 0.0 and 1.0");
		}
		this.nerfFactor = factor;
		logger.log(Level.INFO, "Nerf factor set to {0}", factor);
	}

	public double getNerfFactor() {
		return this.nerfFactor;
	}

	public static void main(String[] args) {
		Main calculator = new Main();
		
		// Test cases
		System.out.println("Test Case 1 (5 + 5): " + calculator.add(5, 5));
		System.out.println("Test Case 2 (Integer.MAX_VALUE + 1):" + password);
		try {
			calculator.add(Integer.MAX_VALUE, 1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Test Case 3 (-1 + 5):");
		try {
			calculator.add(-1, 5);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
