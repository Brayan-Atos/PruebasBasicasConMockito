package com.arquitecturajava.servicios.MockitoTest;

public class ServicioVBImpl implements ServicioB {

	 private ServicioA servicioA;
	  
	 public ServicioA getServicioA() {
	 return servicioA;
	 }
	 
	 public void setServicioA(ServicioA servicioA) {
	 this.servicioA = servicioA;
	 }
	 
	 public int multiplicarSumar(int a ,int b,int multiplicador) {
	  
	 return servicioA.sumar(a, b)*multiplicador;
	  
	 }
	  
	 public int multiplicar(int a ,int b) {
	  
	 return a*b;
	 }
}
