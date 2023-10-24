package com.ipartek.formacion.ejemplos;

public class Operadores {
	public static void main(String[] args) {
		int x = 6, y = 4;

		System.out.println(x == y);
		System.out.println(x > y);

		System.out.println(x + y + 5);

		System.out.println(1 <= x && x <= 10);

		x = y = 0;

		System.out.println(x++); // 0
		System.out.println(x); // 1

		System.out.println(++x); // 2
		System.out.println(x); // 2

		System.out.println(x > y ? x : y); // Mayor de dos números

		boolean b = false;

		System.out.println(b ? "VERDADERO" : "FALSO");

		if (b) {
			System.out.println("VERDADERO");
		} else {
			System.out.println("FALSO");
		}
	}
}
