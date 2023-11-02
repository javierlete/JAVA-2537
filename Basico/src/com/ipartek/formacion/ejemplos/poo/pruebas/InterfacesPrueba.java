package com.ipartek.formacion.ejemplos.poo.pruebas;

import com.ipartek.formacion.ejemplos.poo.interfaces.Balon;
import com.ipartek.formacion.ejemplos.poo.interfaces.Comestible;
import com.ipartek.formacion.ejemplos.poo.interfaces.Naranja;
import com.ipartek.formacion.ejemplos.poo.interfaces.Rodable;

public class InterfacesPrueba {

	public static void main(String[] args) {
		Rodable[] rodables = new Rodable[2];
		
		rodables[0] = new Naranja();
		rodables[1] = new Balon();
		
		for(Rodable r: rodables) {
			if(r instanceof Comestible c) {
				System.out.print("Mordisco: ");
				c.comer();
			}
			r.rodar();
			if(r instanceof Comestible c) {
				System.out.print("Mordisco: ");
				c.comer();
			}
		}
	}

}
