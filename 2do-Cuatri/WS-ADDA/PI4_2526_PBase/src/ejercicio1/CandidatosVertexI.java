package ejercicio1;

import java.util.List;
import java.util.Set;

public record CandidatosVertexI(Integer index, List<Integer> alternativas) implements CandidatosVertex {

	
	public static CandidatosVertex inital() {
		return of(0,List.of());
	}
	
	public static CandidatosVertex of(Integer i, List<Integer> ls){
		return new CandidatosVertexI(i,ls);
	}

	public Boolean goal() {
		return this.index() == Datos1.getNumCandidatos();
	}
	
	public Boolean goalHasSolution() { // Tiene que comprobar si son una solución los elegidos  {candidatos en alternativas} 
		return null; 
	}


	public List<Integer> actions(){
		List<Integer> r;
		// Que compruebe que las cualidades estén cubiertas en vez de si quedan candidatos por cubrir
		if()
	}
	
	// CualidadesACubrir -> Datos1.getCualidades(); valor inicial
	// 	|-> Conjunto que se va reduciendo conforme tomamos decisiones
	// PresupuestoRestante -> Datos1.getPresupuestoMax() ; valor inicial
	// 	|-> Real que se reduce segun Datos1.getSueldoMin(i);

}
