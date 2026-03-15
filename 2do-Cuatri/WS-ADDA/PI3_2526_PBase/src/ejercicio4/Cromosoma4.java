package ejercicio4;


import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import us.lsi.ag.RangeIntegerData;
import us.lsi.ag.agchromosomes.Chromosomes.ChromosomeType;

public class Cromosoma4 implements RangeIntegerData<Solucion4> {
	public Cromosoma4(String file) {
		Datos4.iniDatos(file);
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.RangeInteger;
	}
	
	@Override
	public Integer size() {
		return Datos4.N;
	}

	
	// Camino que pase por todas los vertices una vez y vuelva al origen
	// Esfuerzo total minimo (goal)
	// duracion menor o igual 3/4 suma total duraciones de todas las calles del grafo
	// deben existir al menos 2 intersecciones consecutivas con monumento de interés
	
	
	@Override
	public Double fitnessFunction(List<Integer> value) {
		double goal = 0.; // Equivale a la suma de esfuerzos 
		double error = 0.; 
		// Cada Xi indica la interseccion i-ésima
		// X0 tiene que estar conectado con Xn
		// Al menos 2 intersecciones consecutivas con monumento
		// 2 intersecciones no pueden tener la misma posicion
		
		// Map < Numero de vertice : Numero de orden >
		Map<Integer,Integer> orden = new HashMap<Integer,Integer>();
		
		for(int interseccion = 0; interseccion < size(); interseccion ++) {
			
			int inter = value.get(interseccion);
			if(!orden.containsKey(inter)) {
				orden.put(inter, interseccion);
			} else {
				// En caso de que ya tenga ese vertice,
				// Significa que "vuelve a pasar" por el
				// añadir mucho error para que sea imposible
				error += 100000;
				// Tendría sentido el AuxiliaryAg.* aquí?
			}
			// Es necesario?
		}
		// Luego del for de arriba tenemos un map con cada vertice y el orden en el que se recorre
		
		// Si el numero de claves es igual al de vertices implica
		// que todos son diferentes, entonces podremos empezar a probar
		// si cumplen o no las restricciones
		if(orden.keySet().size() == size()) {
			// R1 -> La primera intersección y la última deben estar conectadas
			List<Integer> x0xN = orden.entrySet().stream().filter(x->x.getValue() == 0 || x.getValue() == Datos4.N-1).sorted(Comparator.comparingInt(x->x.getValue())).map(x->x.getKey()).toList();
			// Lista con los elementos x0xn 
			// Si el tiempo es 1000., no existe
			if(Datos4.tiempo(x0xN.get(0),x0xN.get(1)) == 1000.) {
				error += 100000;
			}
			
			// R2 -> Al menos 2 intersecciones seguidas con monumento
			int contadorMonumentos = 0;
			for(Integer intersec1 : orden.keySet()) {
				for(Integer intersec2 : orden.keySet()) {
					if(intersec1 != intersec2) {
						goal += Datos4.esfuerzo(intersec1,intersec2);
						if(Datos4.sonMonumentos(intersec1, intersec2)) {
							contadorMonumentos +=1;
						}
					}
				}
			}
			if(contadorMonumentos == 0) {
				error += 10000000;
			}
			
			
		}
		
		// Queremos MINIMIZAR el esfuerzo
		return -(goal - error);
	}


	@Override
	public Solucion4 solution(List<Integer> value) {
		return Solucion4.create(value);
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return Datos4.N;
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
 
