package ejercicio3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.RangeIntegerData;
import us.lsi.ag.agchromosomes.Chromosomes.ChromosomeType;
import us.lsi.common.Set2;

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
     */
    
    public Double fitnesFunction(List<Integer> value) {
    	double goal = Set.of(value.stream().filter(c->c<Datos3.getNumContenedores()).toList()).size();
    	double error = 0.;
    	double incompatible = 0;
    	Set<Integer> elementosUbicados = Set2.empty();
    	
    	for(int i=0;i < size();i++) {
    		// Recorro todos los Xi 
    		// si Xi = {0,1,2} -> Xi está en el contenedor 0,1 o 2
    		// si Xi = {3} -> Xi no está en ningun contenedor
    		int contenedor = value.get(i);
    		if(contenedor != Datos3.getNumContenedores()) {
    			// Si está ubicado en 0 1 o 2
    			// Comprobamos si puede estar ubciado
    			if(Datos3.getPuedeUbicarse(i, contenedor)) {
    				// Si puede ubicarse lo añadimos a conjunto de elementos ubicados
    				elementosUbicados.add(i);
    			} else {
    				incompatible ++;
    		}
    		}
    	}
    	// Para los elementos ubicados sumamos aquellos que estén en el mismo contenedor
    	Map<Integer,List<Integer>> contenedorElementos = elementosUbicados.stream().collect(Collectors.groupingBy(x->value.get(x)));
    	for(Integer contenedor : contenedorElementos.keySet()) {
    		double capacidadContenedor = 0;
    		for(Integer elemento : contenedorElementos.get(contenedor)) {
    			capacidadContenedor += Datos3.getTamElemento(elemento);
    		}
    		error += AuxiliaryAg.distanceToEqZero(Datos3.getTamContenedor(contenedor) - capacidadContenedor);
    	}
    	return goal- 100 * (error + AuxiliaryAg.distanceToEqZero(incompatible));
    }
    
    public Double fitnessFunction(List<Integer> value) {
    	int goal = goal(value);
    	Double totalIncompatibilidad = totalIncompatibilidad(value);
    	Double totalTamaño = totalTamaño(value);
    	return goal - 10000*(totalIncompatibilidad+totalTamaño);
    }
    
    private Integer goal(List<Integer> value) {
    	return Set.of(value.stream().filter(c->c<Datos3.getNumContenedores()).toList()).size();
    }
    
    private Double totalIncompatibilidad(List<Integer> value) {
    	Double cont = 0.;
    	for(int i=0; i<value.size(); i++) {
    		if(value.get(i)!=Datos3.getNumContenedores() && Datos3.getNoPuedeUbicarse(i, value.get(i))) {
    			cont++;
    		}
    	}
    	return AuxiliaryAg.distanceToEqZero(cont);
   		}
    
    private Double totalTamaño(List<Integer> value) {
    	
    	Map<Integer, Double> map = new HashMap<>();
    	for(int i=0; i<value.size(); i++) {
    		if(!map.containsKey(value.get(i))) {
    			map.put(value.get(i), 0.);
    		}
    		double suma=map.get(value.get(i));
    		suma+=Datos3.getTamElemento(i);
    		map.put(value.get(i), suma);
    	}
    	double cont = 0;
    	for(int j=0; j<Datos3.getNumContenedores();j++) {
    		if(map.containsKey(j)) {
    			cont+= AuxiliaryAg.distanceToEqZero(map.get(j)- Datos3.getTamContenedor(j));
    		}
    	}
    	return cont;
    }
    	
    
    public Double ff(List<Integer> value) {
    	double goal = 0.; // +1 por cada contenedor completamente lleno
    	double error = 0.;
    	// el cromosoma es mejor por cada contenedor lleno
    	// 2 Opciones 
    		// -> un conetendor solo es valido si contiene elementos compatibles
    		// -> un contenedor es menos valido si contiene elementos no compatibles
    	// Solo se usan elementos si están asignados a un contenedor en uso
    	
    	/*
    	// Recorro todos los elementos
    	for (int i = 0; i < size() ; i++) {
    		// i es el elemento
    		// value.get(i) es el contenedor asignado
    		Datos3.getTamElemento();
    		
    	}*/
    	
    	
    	// recorro los contenedores?
    	
    	for ( int i = 0; i < max(i) ; i ++) {
    		int cap = 0;
    		// j cada elemento del cromosoma
    		for( int j = 0; j < size() ; j++) {
    			int contenedor = value.get(j);
    			// Si puede ubicarse en el contenedor la capacidad del contenedor aumenta
    			// Si no (disminuye)
    			if ( i == contenedor && Datos3.getPuedeUbicarse(j, contenedor)) {
    				cap += Datos3.getTamElemento(i);
    			} else if(i == contenedor && Datos3.getNoPuedeUbicarse(j, contenedor)) {
    				cap -= Datos3.getTamElemento(i);
    			}
    		
    		}
    		if (cap == Datos3.getTamContenedor(i)) {
    			goal +=1;
    		} else {
    			error += cap*100;
    		};
    	}
    	return goal + error*error;
    }
    @Override
    public Solucion3 solution(List<Integer> value) {
        return Solucion3.create(value);
    }

    @Override
    public Integer max(Integer i) {
        return Datos3.getNumContenedores();
    }
    @Override
    public Integer min(Integer i) {
        return 0; 
    }
}