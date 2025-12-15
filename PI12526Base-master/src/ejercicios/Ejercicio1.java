package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Ejercicio1 {

	// Del enunciado:
	public static Map<Integer,List<String>> solucionFuncional(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		
		// Funcion que crea un entero cadena en el que el EnteroCadena.a() se le suman 2 y
		// si este mismo es divisible entre 3 suma la cadena al numero y lo convierte en un string
		// o devuelve una subcadena siendo el indice el módulo de EnteroCadena.a() entre la longitud de EnteroCadena.s()
		UnaryOperator<EnteroCadena> nx = elem -> {
			return EnteroCadena.of(elem.a()+2,
				elem.a()%3==0?
					elem.s()+elem.a().toString():
					elem.s().substring(elem.a()%elem.s().length()));
		};
		/* Recorre un stream de entero cadena
		 * Convierte todos los elementos en el EnteroCadena.s() + la cadena varD
		 * Filtra el stream devolviendo aquellos elementos cuya lonjutd sea menor que varE
		 * Los agrupa luego por longitud
		 */
		return Stream.iterate(EnteroCadena.of(varA,varB), elem -> elem.a() < varC, nx)
					.map(elem -> elem.s() + varD)
					.filter(nom -> nom.length() < varE)
					.collect(Collectors.groupingBy(String::length));
	}
	
	private static EnteroCadena next(EnteroCadena anterior) {
		return EnteroCadena.of(anterior.a()+2,anterior.a()%3==0?
				anterior.s()+anterior.a().toString():
				anterior.s().substring(anterior.a()%anterior.s().length()));
	}
	
	public static Map<Integer,List<String>> solucionIterativa(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		Map<Integer,List<String>> ac = new HashMap<>();
		EnteroCadena actual = EnteroCadena.of(varA, varB);
		String nom;
		int nomLength;
		while(actual.a() < varC ) {
			nom = actual.s() + varD;
			nomLength = nom.length();
			if(nomLength < varE) {
				if (!ac.containsKey(nomLength)){
					ac.put(nomLength,new ArrayList<String>());
					ac.get(nomLength).add(nom);
				} else {
					ac.get(nomLength).add(nom);
				}
			}
			actual = next(actual);
		}
		return ac;
	}
	
	public static Map<Integer,List<String>> sol(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		Map<Integer,List<String>> ac = null;
		EnteroCadena actual = EnteroCadena.of(varA, varB);
		String nom = "";
		String nxB;
		int nomLength = 0;
		nom = actual.s() + varD;
		nomLength = nom.length();
		
		if(actual.a()%3 == 0) {
			nxB = actual.s()+actual.a().toString();
		} else {
			nxB = actual.s().substring(actual.a()%actual.s().length());
		}
		
		if(actual.a() < varC) {
			ac = sol(varA+2,nxB,varC,varD,varE);
			if(nomLength < varE) {
				if(ac.containsKey(nomLength)) {
					ac.get(nomLength).add(nom);					
				} else {
					ac.put(nomLength, new ArrayList<String>());
					ac.get(nomLength).add(nom);					
				}
			}		
		// Al ser el caso base asumo que nunca va a entrar aquí de primeras
		} else {
			return new HashMap<>();
		}
		return ac;
	}	
	// Me falta la recursiva final
	public static Map<Integer,List<String>> solucionRecursivaFinal(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		return null;
	}


}
