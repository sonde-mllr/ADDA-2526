package ejemplos.casa;

public class Ejemplo4 {
	public static Double potAN_recNoFinal(Double a, Integer n) {
		Double res = null;
		if(n==0) {
			return 1.;
		} else {
			res = a*potAN_recNoFinal(a,n-1);
		}
		return res;
	}
	
	public static Double potAN_it(Double a,Integer n) {
		Double res = a;
		while(n > 1) {
			res *=a;
			n-=1;
		}
		return res;
	}
	
	 // porque me apetece
	/*
	public static Double potAN_recFinal(Double a,Integer n) {
		Double res = null;
		if(n==1) {
			return a;
		} else {
			res = potAN_recFinal(a+a,n-1);
		}
		return res;
	}
	

	
	public static Double potAN_it2(Double a,Integer n) {
		Double res = a;
		for(int i = 0; i<n-1;i++) {
			res *= a;
		}
		return res;
	}
	*/
	
}
