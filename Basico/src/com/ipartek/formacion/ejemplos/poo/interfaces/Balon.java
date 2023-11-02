package com.ipartek.formacion.ejemplos.poo.interfaces;

public class Balon extends ObjetoDeporte implements Rodable {

	@Override
	public void rodar() {
		System.out.println("Rodando un balón");
	}

}
