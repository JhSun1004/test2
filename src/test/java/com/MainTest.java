package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void test() {
		Main main = new Main();
		int result = main.add(1, 1);
		assertEquals(2, result);
	}

	@Test
	void testNerfFactorSetting() {
		Main main = new Main();
		main.setNerfFactor(0.5);
		assertEquals(0.5, main.getNerfFactor());
	}

	@Test
	void testNerfFactorApplication() {
		Main main = new Main();
		main.setNerfFactor(0.5);
		int result = main.add(10, 10);
		assertEquals(10, result); // 20 * 0.5 = 10
	}

	@Test
	void testNerfFactorNoNerf() {
		Main main = new Main();
		main.setNerfFactor(1.0);
		int result = main.add(5, 5);
		assertEquals(10, result); // No nerf applied
	}

	@Test
	void testInvalidNerfFactorTooHigh() {
		Main main = new Main();
		assertThrows(IllegalArgumentException.class, () -> {
			main.setNerfFactor(1.5);
		});
	}

	@Test
	void testInvalidNerfFactorTooLow() {
		Main main = new Main();
		assertThrows(IllegalArgumentException.class, () -> {
			main.setNerfFactor(-0.1);
		});
	}

}
