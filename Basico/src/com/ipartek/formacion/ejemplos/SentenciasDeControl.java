package com.ipartek.formacion.ejemplos;

public class SentenciasDeControl {
	public static void main(String[] args) {
		int num = 4;

		if (num > 5) {
			System.out.println("El número es mayor que 5");
		} else if (num == 5) {
			System.out.println("El número es igual que 5");
		} else {
			System.out.println("El número es menor que 5");
		}

		int opcion = 2;

		switch (opcion) {
		case 1:
			System.out.println("Opción 1");
			break;
		case 2:
			System.out.println("Opción 2");
			break;
		case 3:
			System.out.println("Opción 3");
			break;
		default:
			System.out.println("Opción DESCONOCIDA");
		}

		num = 1;
		while (num <= 5) {
			System.out.print(num);
			num++;
		}

		System.out.println();
		
		num = 1;
		do {
			System.out.print(num);
			num++;
		} while (num <= 5);
		
		System.out.println();
		
		for(int i = 1; i <= 5; i++) {
			System.out.print(i);
		}
	}
}
