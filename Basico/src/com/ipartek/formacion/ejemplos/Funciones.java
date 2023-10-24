package com.ipartek.formacion.ejemplos;

import java.math.BigInteger;

public class Funciones {
	public static void main(String[] args) {
		System.out.println(sumar(6, 7));
		System.out.println(sumar(6, 7, 8));

		System.out.println(factorial(3));
		System.out.println(factorial(20));
		System.out.println(factorial(new BigInteger("10000")));

		System.out.println(factorialRecursivo(20));
		System.out.println(factorialRecursivo(3));
		System.out.println(factorialRecursivo(new BigInteger("10000")));
		
		System.out.println("FIN");
	}

	public static int sumar(int a, int b) {
		return a + b;
	}

	// Sobrecarga
	public static int sumar(int a, int b, int c) {
		return a + b + c;
	}

	/**
	 * 5! == 5 * 4 * 3 * 2 * 1
	 * 
	 * @param numero
	 * @return factorial de numero
	 */
	public static long factorial(long numero) {
		long total = 1;

		for (long paso = numero; paso > 1; paso--) {
			total *= paso;
		}

		return total;
	}

	public static BigInteger factorial(BigInteger numero) {
		BigInteger total = BigInteger.ONE;

		for (BigInteger paso = numero; paso.compareTo(BigInteger.ONE) > 0; paso = paso.subtract(BigInteger.ONE)) {
			total = total.multiply(paso);
		}

		return total;
	}
	
	/**
	 * 5! = 5 * 4!
	 * 4! = 4 * 3!
	 * 3! = 3 * 2!
	 * 2! = 2 * 1!
	 * 1! = 1
	 * @param numero
	 * @return
	 */
	public static long factorialRecursivo(long numero) {
		if(numero == 1) {
			return 1;
		}
		
		return numero * factorialRecursivo(numero - 1);
	}
	public static BigInteger factorialRecursivo(BigInteger numero) {
		if(numero.equals(BigInteger.ONE)) {
			return BigInteger.ONE;
		}
		
		return numero.multiply(factorialRecursivo(numero.subtract(BigInteger.ONE)));
	}
}
