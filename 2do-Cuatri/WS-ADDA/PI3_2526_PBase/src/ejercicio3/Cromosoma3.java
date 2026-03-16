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
    
    public Double fitnessFunction(List<Integer> value) {
    	double goal = 0.;
    	double error = 0.;
    	double malUbicado = 0;
    	
    	//Set.of(value.stream().filter(c->c<Datos3.getNumContenedores()).toList())
    	// List<Integer> elementosAsignados = value.stream().filter(c->c<Datos3.getNumContenedores()).toList();
    	//int nElementosAsignados = elementosAsignados.size();
    	//System.out.println(value);
    	//System.out.println(elementosAsignados);
    	//System.out.println(Datos3.getNumContenedores());
    	// Calcular carga de cada contenedor
    	// Para Xi {0,1,2,3} -> Si Xi = 3 Se asignan a ese contenedor (No existe)
    	
    	int[] capacidades = new int[Datos3.getNumContenedores()];
    	for(int i=0;i<size();i++) {
    		int contenedor = value.get(i);
    		if(contenedor != Datos3.getNumContenedores()) {
    			if(Datos3.getNoPuedeUbicarse(i, contenedor)) {
    				// Si está ubicado y Está mal ubicado -> error
    				// Qué error?
    				// Contador -> Luego AuxiliaryAg.DistancetoEq();
    				malUbicado +=1;
    			}
    			// Sumo al array de capacidades en la posicion del contenedor correspondiente el tamaño del elemento
    			capacidades[contenedor] += Datos3.getTamElemento(i);
    		}
    	}
    	// Comprobar que los contenedores están llenos
    	for(int j = 0;j<Datos3.getNumContenedores();j++) {
    		// Contenedor es cada posicion del array
    		// La primera vez que se recorra es la capacidad acumulada del contenedor 1
    		// La segunda la del segundo ....
    		double capContenedor = capacidades[j];
    		if(capContenedor == Datos3.getTamContenedor(j)) {
    			goal += 1;
    		} else {
    			error += AuxiliaryAg.distanceToEqZero(Datos3.getTamContenedor(j)-capContenedor);
    		}
    	}
    	
    	double distanciaTotal = error + AuxiliaryAg.distanceToEqZero(malUbicado); // TODO

    	return goal - 1000*distanciaTotal;
    }
    /*
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
    	*/
    
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
        return Datos3.getNumContenedores()+1;
    }
    @Override
    public Integer min(Integer i) {
        return 0; 
    }
}