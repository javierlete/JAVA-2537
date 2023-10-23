package com.ipartek.formacion.ejemplos;

public class Conversiones {
	public static void main(String[] args) {
		String numTexto = "5";
		String num2Texto = "6.7";
		
		int num = Integer.parseInt(numTexto);
		double num2 = Double.parseDouble(num2Texto);
		
		System.out.println(num + num2);
		
		String s = "si";

		char c = s.charAt(0);
		System.out.println(c);
		
		boolean b = "si".equals(s);
		System.out.println(b);
		
		System.out.println(String.valueOf(num) + "2");
		
		System.out.println("" + num + "2");
	}
}
