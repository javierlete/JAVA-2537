package com.ipartek.formacion.ejemplos.poo.pruebas;

public class NumberPrueba {

	@SuppressWarnings("removal")
	public static void main(String[] args) {
		Number[] numeros = new Number[2];
		
		numeros[0] = new Integer(1);
		numeros[1] = new Double(2.3);
		
		for(Number n: numeros) {
			System.out.println(n.doubleValue());
		}
	}

}
