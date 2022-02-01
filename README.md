# PruebasBasicasConMockito
En este software se implementan los primeros test basicos con Mockito para ver su funcionamiento: 

Se tienen 2 servicios, el servicio A y el servicio B ambos con distintas implementaciones 
## interfaz Servicio A:
```
public interface ServicioA {
	public abstract int sumar(int a, int b);
}
```
## interfaz Servicio B:
```
public interface ServicioB {
	 public ServicioA getServicioA();
	 
	 public void setServicioA(ServicioA servicioA);
	  
	 public int multiplicarSumar(int a, int b, int multiplicador);
	 
	 public int multiplicar(int a, int b);
}
```
## Implementacion del servicio A: 
```
public class ServicioAImpl implements ServicioA {
	
	public int sumar(int a ,int b) {
		  
		 return a+b;
		 }
}
```
## Implementacion del servicio B:
```
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
```

Con este codigo representamos el siguiente diagrama:
![](https://www.arquitecturajava.com/wp-content/uploads/Mockito.png)

En el el servicio A depende del Servicio B son unas clases sencillas en las que se realiza una suma y una multiplicacion, pero la multiplicacion del servicioB depende de la suma del servicioA. 
Lo siguiente que hay que realizar con las pruebas unitarias para verificar que el codigo este funcionando de manera optima:
```
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
	  
	 ServicioA servicioA=new ServicioAImpl();
	  
	 ServicioB servicioB= new ServicioVBImpl();
	  
	  
	 servicioB.setServicioA(servicioA);
	 Assert.assertEquals(servicioB.multiplicarSumar(2, 3, 2),10);
	 System.out.println("Prueba pasada"); 
	 }
}
```

Estas pruebas funcionan sin problema, ***Pero que pasa si nosotros decidimos realizar un cambio en el metodo del servicioA de suma****.
Nosotros al realizar un cambio en este metodo:
```
public class ServicioAImpl implements ServicioA {
	
	public int sumar(int a ,int b) {
		  
		 return a+b+1;
		 }
}
```
El cual fue al momento de retorno de un valor agregarle un 1 a la suma, las pruebas fallaran, ya que el resultado con el cual las estamos comparando cambiara y no dara lo esperado.
![image](https://user-images.githubusercontent.com/98105255/152010516-12e8871e-f584-4e86-9828-9161b541a436.png)

***Pero con Mockito podemos aislar los objetos en testeo para evitarnos este problema***
Para evitar este tipo de problema debemos aislar las pruebas unitarias y una de las opciones es con Java Mockito y crear un Mock Object. Los object Mock nos permite simular ser un objeto real y eliminan dependencias, permitiendo que los test se ejecuten de manera aislada:
![image](https://user-images.githubusercontent.com/98105255/152011596-1f65a735-5c1a-4329-818b-6e788b5ff067.png)

```
@Test
	 public void testmultiplicarSumar() {
	  
		ServicioA servicioA=mock(ServicioA.class);
		when(servicioA.sumar(2,3)).thenReturn(5);
	  
	 ServicioB servicioB= new ServicioVBImpl();
	  
	  
	 servicioB.setServicioA(servicioA);
	 Assert.assertEquals(servicioB.multiplicarSumar(2, 3, 2),10);
	 System.out.println("Prueba pasada"); 
	 }
```
Al modificar nosotros el metodo testMultiplicarSumar() y añadirle un objeto mock nosotros aislamos esa parte del servicioA que pueda ser la que ocacione problemas, nosotros al correr nuevamente las pruebas este fue el resultado: 
![image](https://user-images.githubusercontent.com/98105255/152012289-3ab3d3fc-ba94-4c34-a295-6c4af653f47d.png)
Teniendo solo un fallo ahora, el cual es en la clase suma donde se encuentra el error, esto sirve para que los desarrolladores puedan encontrar mucho mas rapido los erroes 
