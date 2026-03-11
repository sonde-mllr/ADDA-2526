package ejercicio4;


import java.util.List;

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
		// TODO Tantos genes como candidatos
		return null;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		return null;
	}


	@Override
	public Solucion4 solution(List<Integer> value) {
		return Solucion4.create(value);
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
 
