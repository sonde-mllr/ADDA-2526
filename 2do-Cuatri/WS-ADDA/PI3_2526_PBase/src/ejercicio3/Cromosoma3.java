package ejercicio3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.ag.RangeIntegerData;
import us.lsi.ag.agchromosomes.Chromosomes.ChromosomeType;

public class Cromosoma3 implements RangeIntegerData<Solucion3> {


    public Cromosoma3(String file) {
        Datos3.iniDatos(file);
    }

    @Override
    public ChromosomeType type() {
        return ChromosomeType.RangeInteger;
    }

    @Override
    public Integer size() {
        return Datos3.getNumElementos();
    }

    
    /*
     * Conj elementos y conj contenedores
     * Contenedor -> tipo y capacidad
     * Elemento -> tipoContenedor y tamaño
     * GOAL = ocupar totalmente mayor numero contenedores 
     * 
     * Datos entrada:
     *  n: elementos
     *  ei: tamaño elemento i,i en [0,n)
     *  
     *  m: contenedores
     *  cj: tamaño contenedor
     *  wij: si elemento i en contenedor j
     *  
     *  Cromosoma Xi = m
     *  el elemento i-éismo no ubicado en contenedor m
     *  
     *  [1,2,3, ... ] -> Elemento 1 no está en contenedor 1 ...
     *  
     *  Quiero hacerlo para que indique que sí está 
     */
    @Override
    /* Con diccionario
    public Double fitnessFunction(List<Integer> value) {
     
    	// Goal 1 si contenedor completamente lleno
    	// Completamente lleno si suma de los pesos de elementos del contenedor = capacidad contenedor
    	// Cada elemento maximo 1 contenedor
    	
    	double goal = 0.;
    	double error = 0.;
    	int contadorMalUbicados = 0;
    	// Map que asocia contenedor y capacidad
    	Map<Integer,Integer> capacidades = new HashMap<Integer, Integer>();
    	for( int i = 0; i < size(); i++) {
    		// i es el elemento
    		int contenedor = value.get(i);
    		// si es 0 no está ubicado
    		if(contenedor > 0) {
    			
    			// Añadir a capacidades
    			if(capacidades.containsKey(contenedor)) {
    				int tamTotal = capacidades.get(contenedor) + Datos3.getTamElemento(i);
    				capacidades.put(contenedor, tamTotal);
    			} else {
    				capacidades.put(contenedor, Datos3.getTamElemento(i));
    			}
    			// Comprobar si se puede ubicar
    			if(Datos3.getNoPuedeUbicarse(i, contenedor)) {
    				contadorMalUbicados += 1;
    			}
    		}
    	}
    	    	
    	// Luego del for tengo un map con la capacidad de cada contendor
    	// si la capacidad es mayor o menor  a la del contenedor se añade error
    	
    	for( Integer contenedor : capacidades.keySet()) {
    		// añade más o menos error dependiendo de la diferencia
    		//error +=  * Math.abs((Datos3.getTamContenedor(contenedor) - capacidades.get(contenedor)));
    		// El if añadiría error solo si se cumple la condicion
    		
    		Integer tamMaxContenedor = Datos3.getTamContenedor(contenedor);
    		Integer tamOcupadoContenedor = capacidades.get(contenedor);
    		
    		if(tamMaxContenedor == tamOcupadoContenedor) {
    			goal += 1;
    		} else {
    			error += Math.abs(tamMaxContenedor - tamOcupadoContenedor);
    		}
    	}
    	
        return goal -   error * contadorMalUbicados;
    }*/
    
    public Double fitnessFunction(List<Integer> value) {
    	double goal = 0.;
    	double error = 0.;
    	int malUbicado = 0;
    	
    	for(int i = 0; i < max(i) ; i++) {
    		contenedor = value.get(i);
    	}
    	
    	return goal - malUbicado;
    }

    @Override
    public Solucion3 solution(List<Integer> value) {
        return Solucion3.create(value);
    }

    @Override
    public Integer max(Integer i) {
        return Datos3.getNumContenedores() + 1;
    }
    @Override
    public Integer min(Integer i) {
        return 0; 
    }
}