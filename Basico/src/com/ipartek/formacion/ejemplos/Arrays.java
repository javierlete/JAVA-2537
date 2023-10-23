package com.ipartek.formacion.ejemplos;

public class Arrays {
	public static void main(String[] args) {
		int[] arr = new int[3];
		
		arr[0] = 5;
		arr[1] = 6;
		arr[2] = 7;
//		arr[3] = 8; // ArrayIndexOutOfBoundsException
//		arr[2] = "6"; // No es del mismo tipo que el del array
		
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		
		System.out.println();
		
		for(int dato: arr) {
			System.out.print(dato);
		}
	}
}
