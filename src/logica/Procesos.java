package logica;

import entidades.Estudiantes;

public class Procesos {
	
	public double calcularPromedio(Estudiantes miEstudiante) {
		System.out.println("Estamos en Procesos");
		double n1=miEstudiante.getNota1();
		double n2=miEstudiante.getNota2();
		double n3=miEstudiante.getNota3();
		
		double prom = -1;
		if(validar(n1,n2,n3)==true) {
		prom=(n1+n2+n3)/3;
		}
		return prom;
		}
	public boolean validar(double n1,double n2,double n3) {
		if (n1>=0 && n1<=5 && n2>=0 && n2<=5 && n3>=0 && n3<=5) {
			return true;
		}
			return false;
	}
}