package com.arquitecturajava.servicios.MockitoTest;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import com.arquitecturajava.servicios.MockitoTest.ServicioA;
import com.arquitecturajava.servicios.MockitoTest.ServicioAImpl;

public class TestServicioA {
	@Test
	public void testSuma() {
		int a = 2;
		int b = 2;
		ServicioA servicio = new ServicioAImpl();
		Assert.assertEquals(servicio.sumar(a, b),4);
		System.out.println("Prueba Realizada");
	}
	
	@Test
	 public void testMultiplicacion() {
	  
	 ServicioB servicioB= new ServicioVBImpl();
	 Assert.assertEquals(servicioB.multiplicar(2, 3),6);
	 System.out.println("Prueba pasada"); 
	 }
	
	@Test
	 public void testmultiplicarSumar() {
	  
	 ServicioA servicioA= mock(ServicioA.class);
	  when(servicioA.sumar(2, 3)).thenReturn(5);
	 ServicioB servicioB= new ServicioVBImpl();
	  
	  
	 servicioB.setServicioA(servicioA);
	 Assert.assertEquals(servicioB.multiplicarSumar(2, 3, 2),10);
	 System.out.println("Prueba pasada"); 
	 }
}
