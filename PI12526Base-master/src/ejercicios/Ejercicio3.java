package ejercicios;

import java.util.HashMap;
import java.util.Map;

import us.lsi.common.Trio;

public class Ejercicio3 {

	public static Long iterativo(Integer a, Integer b, Integer c) {
		Map<Trio<Integer,Integer,Integer>,Long> res = new HashMap<>();
		Long v;
		for(int i = 0;i <= a;i++) {
			for(int j = 0;j<=b;j++) {
				for (int k = 0; k <= c;k++ ) {
					if(i<3 || j<3 || k<3) {
						v = (long) (i+j*j+2*k);
					} else if(a%b == 0) {
						v = res.get(Trio.of(i-1, j/2, k/2)) + res.get(Trio.of(i-3, j/3, k/3));
					} else {
						v = res.get(Trio.of(i/3, j-3, k-3)) + res.get(Trio.of(i/2, j-2, k-2));
					}
					res.put(Trio.of(i, j, k), v);
				}
			}
		}
		return res.get(Trio.of(a, b, c));
	}

	public static Long recursivo_sin_memoria(Integer a, Integer b, Integer c) {
		Long r;
		if(a<3 || b<3 || c<3) {
			return (long) (a+b*b+2*c);
		} else if(a%b==0) {
			r = recursivo_sin_memoria(a-1,b/2,c/2)+recursivo_sin_memoria(a-3,b/3,c/3);
		} else {
			r  = recursivo_sin_memoria(a/3,b-3,c-3)+recursivo_sin_memoria(a/2,b-2,c-2);
		}
		return r;
	}
	
	
	
	public static Long recursivo_con_memoria(Integer a, Integer b, Integer c) {		
		return recConMem(a,b,c,new HashMap<>());
	}
	
	private static Long recConMem(Integer a,Integer b,Integer c,Map<Trio<Integer,Integer,Integer>,Long> m) {
		Long r;
		Trio<Integer,Integer,Integer> key = Trio.of(a,b,c);
		if(m.containsKey(key)){
			return m.get(key);
		}else if(a<3 || b<3 || c<3) {
			return (long) (a+b*b+2*c);
		} else if(a%b==0) {
			r = recursivo_sin_memoria(a-1,b/2,c/2)+recursivo_sin_memoria(a-3,b/3,c/3);
		} else {
			r  = recursivo_sin_memoria(a/3,b-3,c-3)+recursivo_sin_memoria(a/2,b-2,c-2);
		}
		m.put(key,r);
		return m.get(Trio.of(a, b, c));
	}

}
