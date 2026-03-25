package ejercicio1;

import java.util.List;

public interface CandidatosVertex {
	Integer index();
	List<Integer> alternativas(); // Alternativas son los candidatos disponibles
	
	//String toGraph();
	
	public static CandidatosVertex initial() {
		return CandidatosVertexI.of(0,List.of());
	}
}
