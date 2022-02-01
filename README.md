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
		  
		 return a+b+1;
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

