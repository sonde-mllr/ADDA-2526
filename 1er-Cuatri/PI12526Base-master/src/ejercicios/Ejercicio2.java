package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Ejercicio2 {
	
	public static List<Integer> f_RNF (Integer a, Integer b) {
		List<Integer> acum = null;
		if(a<2 || b<2) {
			acum = new ArrayList<>();
			acum.add(a*b);
			return acum;
		} else if (a > b) {
			acum = f_RNF(a%b,b-1);
			acum.add(a);
		} else {
			acum = f_RNF(a-2,b/2);
			acum.add(b);
		}
		return acum;
	}
	
	public static List<Integer> f_it (Integer a, Integer b) {
		List<Integer> acum = new ArrayList<>();
		while(!(a<2 || b<2)) {
			if(a>b) {
				acum.add(0,a);
				a = a%b;
				b--;
			} else {
				acum.add(0,b);
				a = a-2;
				b= b/2;
			}
		}
		acum.add(0,a*b);
		return acum;
	}
	
	
	public static List<Integer> f_RF (Integer a, Integer b) {
		List<Integer> acum = new ArrayList<>();
		return recFinal(acum,a,b);
	}
	
	private static List<Integer> recFinal(List<Integer> acum, Integer a,Integer b){
		List<Integer> aux = acum;
		if(a<2 || b<2) {
			acum.add(0,a*b);
		} else if (a>b) {
			aux.add(0,a);
			aux = recFinal(aux, a%b, b-1); 
		} else {
			aux.add(0,b);
			aux = recFinal(aux,a-2,b/2);
		}
		return aux;
	}
	
	private static record Tupla(Integer a, Integer b,List<Integer> acum) {
		public static Tupla first(Integer a,Integer b) {
			return new Tupla(a,b,new ArrayList<>());
		}
		public static Tupla of(Integer a,Integer b,List<Integer> acum) {
			return new Tupla(a,b,acum);
		}
		public Tupla nx1(List<Integer> acum) {
			acum.add(0,a);
			return of(a%b,b-1,acum);
		}
		public Tupla nx2(List<Integer> acum) {
			acum.add(0,b);
			return of(a-2,b/2,acum);
		}
	}
	
	public static List<Integer> f_funcional (Integer a, Integer b) {
		Tupla t = Stream.iterate(Tupla.first(a, b),e -> e.a()>e.b() ? e.nx1(e.acum()):e.nx2(e.acum()))
				.filter(e->e.a() <2 || e.b() <2).findFirst().get();
		// Falta añadir el caso base
		return t.acum();	
	}

}
