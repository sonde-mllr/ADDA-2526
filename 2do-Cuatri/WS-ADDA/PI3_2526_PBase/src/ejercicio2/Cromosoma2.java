package ejercicio2;

import java.util.ArrayList;
import java.util.List;

import us.lsi.ag.RangeIntegerData;
import us.lsi.ag.agchromosomes.Chromosomes.ChromosomeType;

public class Cromosoma2 implements RangeIntegerData<Solucion2> {
	public Cromosoma2(String file) {
		Datos2.iniDatos(file);
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.RangeInteger;
	}
	
	@Override
	public Integer size() {
		// TODO Tantos genes como candidatos
		return Datos2.getNumProductos();
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		// Objetivo : max suma precios productos
		double goal = 0.;
		double error = 0.;
		int tp = 0;
		int te = 0;
		List<Integer> numProd = new ArrayList<Integer>();
		for(int i = 0; i < size(); i++) {
			if(value.get(i) > 0) {
				goal += Datos2.getPrecioProd(i) * value.get(i);
				numProd.add(value.get(i));
				tp += Datos2.getTiempoProdProd(i) * value.get(i);
				te += Datos2.getTiempoElabProd(i) * value.get(i);
				// Restricciones que causan errores que empeoran el fitness del cromosoma
				// El numero de unidades a la semana debe ser inferior al maximo de ese prod
				/*if(value.get(i) > Datos2.getUnidsSemanaProd(i)) {
					error += 3000*value.get(i);
				}*/
				// max ya gestiona que el valor no se pase
				//error += Datos2.getUnidsSemanaProd(i) - value.get(i);
			}
			
		}
		
		// El Tiempo de produccion y elaboración total debe ser inferior al límite
		error +=  Datos2.getTiempoProdTotal() -tp;
		error +=  Datos2.getTiempoElabTotal() -te;
	    double penalizacion = Datos2.getNumProductos() * 5 * 2085; // n * max_u * precio_max
		return goal - penalizacion * eabs(error);
	}

	
	private double eabs(Double d) {
		return d > 0 ? d: -d;
	}

	@Override
	public Solucion2 solution(List<Integer> value) {
		return Solucion2.create(value);
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return Datos2.getProducto(i).max();
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
 