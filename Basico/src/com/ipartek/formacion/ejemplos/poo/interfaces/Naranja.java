package com.ipartek.formacion.ejemplos.poo.interfaces;

public class Naranja extends Fruto implements Comestible, Rodable {
	private boolean sucia = false;

	@Override
	public void rodar() {
		System.out.println("Rodando un naranja");

		sucia = true;
	}

	@Override
	public void comer() {
		if (sucia) {
			System.out.println("PUAGGGGGG");
		} else {
			System.out.println("Comiendo un naranja");
		}
	}

}
