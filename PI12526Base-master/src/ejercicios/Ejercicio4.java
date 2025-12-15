package ejercicios;

import java.math.BigInteger;

public class Ejercicio4 {
	
	public static Double funcRecDouble(Integer a) {
		Double res = null;
		if(a < 10) {
			res = 5.;
		} else {
			res = Math.sqrt(3*a)*funcRecDouble(a-2);
		}
		return res;
	}
	
	public static BigInteger funcRecBig(Integer a) {
		BigInteger res = null;
		if (a < 10) {
			res = BigInteger.valueOf(5L);
		} else {
			res = funcRecBig(a-2).multiply(BigInteger.valueOf((long) Math.sqrt(3*a)));
		}
		return res;
	}
	
	public static Double funcItDouble(Integer a) {
		return null;
	}
	
	public static BigInteger funcItBig(Integer a) {
		return null;
	}

}