package ejercicio1;

import java.util.List;
import java.util.Set;

import us.lsi.ag.BinaryData;
import us.lsi.ag.agchromosomes.Chromosomes.ChromosomeType;
import us.lsi.common.Set2;

public class Cromosoma1 implements BinaryData<Solucion1> {
	public Cromosoma1(String file) {
		//TODO 
		Datos1.iniDatos(file);
	}

	@Override
	public ChromosomeType type() {
		// TODO Cromosoma de tipo binario
		return ChromosomeType.Binary;
	}
	
	@Override
	public Integer size() {
		// TODO Tantos genes como candidatos
		return Datos1.getNumCandidatos();
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		// TODO Implementar segun el modelo
		double goal = 0; // Objetivo = valoracion maxima
		Double sueldoMin = 0.;
		Set<String> cualidades = Set2.empty();
		Set<Integer> candidatos = Set2.empty();
		// Recorro el cromosoma (candidatos)
		for(int i = 0 ; i < size() ; i++) {
			// Si el candidato es seleccionado (1) lo añado al conjunto de candidatos
			if(value.get(i) > 0) {
				goal += Datos1.getValoracion(i);
				sueldoMin += (Datos1.getSueldoMin(i));
				cualidades.addAll(Datos1.getCualidades(i));
				candidatos.add(i);
			}
		}
		
		Double error = 0.;
		// Si las cualidades de los candidatos seleccionados no continenen las cualidades requeridas hay un error
		if(!cualidades.containsAll(Datos1.getCualidades())) {
			error += 1000;
		}
		
		if(sueldoMin > Datos1.getPresupuestoMax()) {
			error += 1000;
		}
		
		for(Integer candidato1 : candidatos) {
			for(Integer candidato2: candidatos) {
				if(candidato2 > candidato1 && Datos1.getSonIncompatibles(candidato1, candidato2)) {
					error += 1000;
				}
			}
		}
		
		
		// Como estamos maximizando el valor a devolver debe ser positivo
		// cuanto mayor goal, mejor candidato
		// Si contiene errores debe ser negativo
		return goal - error;
	}

	@Override
	public Solucion1 solution(List<Integer> value) {
		return Solucion1.create(value);
	}

}