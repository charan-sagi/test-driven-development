package com.tdd.tautology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tdd.tautology.NumberValidator;

class NumberValidatorTests {

//	@Test
//	public void checkPrimeNumbers() {
//		Integer[] numbers = {1, 15, 23, 25, 60, 61, 63, 79, 207};
//		NumberValidator validator = new NumberValidator();
//		
//		for (int i = 0; i < numbers.length; i++) {
//			boolean isPrime = true;
//			int maxDivisor = (int)Math.sqrt(numbers[i]);
//			for (int counter = 2; counter < maxDivisor; counter++) {
//				if (numbers[i] % counter == 0) {
//					isPrime = false;
//				}
//			}
//			assertEquals(isPrime, validator.isItPrime(numbers[i]));
//		}
//	}
	
	NumberValidator validator;
	Integer[] numbers;
	
	@BeforeEach
	public void setup() {
		validator = new NumberValidator();
	}
	
	@Test
	public void checkPrimeNumber() {
		numbers = new Integer[]{1, 23,61, 79};		
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(true, validator.isItPrime(numbers[i]));
		}
	}
	
	@Test
	public void checkNonPrimeNumber() {
		numbers = new Integer[]{15, 25, 60, 63, 207};
		validator = new NumberValidator();
		
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(false, validator.isItPrime(numbers[i]));
		}
	}

}
