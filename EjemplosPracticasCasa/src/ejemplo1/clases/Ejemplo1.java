package ejemplo1.clases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.geometria.Cuadrante;
import us.lsi.geometria.Punto2D;

public class Ejemplo1 {

	public static Map<Cuadrante, Double> solucionFuncional (List<Punto2D> ls) {
		return ls.stream()
		.collect(Collectors.groupingBy(Punto2D::cuadrante,
		Collectors.reducing(0., p -> p.x(), (a, b) -> a + b)));
		}
	
	// Solución iterativa
	
	public static Map<Cuadrante, Double> solucionIterativa(List<Punto2D> ls){
		Map<Cuadrante, Double> res = new HashMap<Cuadrante, Double>();
		int i = ls.size();
		while(i > 0) {
			i--;
			Punto2D puntillo = ls.get(i);
			if (!res.containsKey(puntillo.cuadrante())){
				res.put(puntillo.cuadrante(),puntillo.x());
			} else {
				res.put(puntillo.cuadrante(),res.get(puntillo.cuadrante()) + puntillo.x() );
			}
		}
		return res;
	}
	
	public static Map<Cuadrante, Double> solucionRecursivaFinal(List<Punto2D> ls){
		Map<Cuadrante,Double> aux = new HashMap<>();
		return solRecFinal(ls,aux,0);
	}

	private static Map<Cuadrante, Double> solRecFinal(List<Punto2D> ls, Map<Cuadrante, Double> aux, Integer contador) {
		Map<Cuadrante,Double> res = aux;
		if(contador == ls.size()) {
			aux.put(ls.get(contador).cuadrante(),ls.get(contador).x());
		} else {
			aux.put(ls.get(contador).cuadrante(),aux.get(contador)+ls.get(contador).x());
			res = solRecFinal(ls,aux,contador+1);
		}
		return res;
	}
}
