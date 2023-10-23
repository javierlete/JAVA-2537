package com.ipartek.formacion.ejemplos;

import java.util.ArrayList;

public class Colecciones {
	public static void main(String[] args) {
		var al = new ArrayList<Integer>();
		
		al.add(5);
		al.add(6);
		al.add(7);
		al.add(8);

		// 5678
		
		al.remove(1); // 578
		al.add(2, 9); // 5798
		al.set(0, 3); // 3798
		
		for(int dato: al) {
			System.out.println(dato);
		}
		
		System.out.println(al.get(2));
	}
}
