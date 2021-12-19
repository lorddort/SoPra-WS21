package de.unistuttgart.iste.sopraws20.api.calculator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.unistuttgart.iste.sopraws20.api.statistic.Calculator;

class CalculatorTest {

	@Test
	void testCorrelationCalc() {
		float X[] = { 15, 18, 21, 24, 27 };
		float Y[] = { 25, 25, 27, 31, 32 };
		float corr;
		corr = Calculator.simpleCorrelationCoefficient(X, Y);
		assertTrue(corr > 0.953463 || corr < 0.953464);
	}

}
