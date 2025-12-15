package tests;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import ejercicios.Ejercicio4;
import us.lsi.common.Pair;
import us.lsi.common.String2;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestEjercicio4 {

	private static Integer nMin = 100; // n mínimo para el cálculo 
	private static Integer nMax = 100000; // n máximo para el cálculo 
	private static Integer razon = 3330; // incremento en los valores de n del cálculo
	private static Integer nIter = 50; // número de iteraciones para cada medición de tiempo
	private static Integer nIterWarmup = 10000; // número de iteraciones para warmup

	
	public static void genData (Consumer<Integer> algorithm, String file) {
		Function<Integer,Long> f1 = GenData.time(algorithm);
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,razon,nIter,nIterWarmup);

	}

	public static void show(Fit pl, String file, String label) {
		List<WeightedObservedPoint> data = DataFile.points(file);
		pl.fit(data);
		MatPlotLib.show(file, pl.getFunction(), String.format("%s = %s",label,pl.getExpression()));
	}
	
	public static void showCombined() {
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros_generados/p1/potRecursiva.txt","ficheros_generados/p1/potIterativa.txt","ficheros_generados/p1/potLog.txt"), 
				List.of("Recursiva","Iterativa","Log"));
	}
	
	public static void main(String[] args) {
		genData(t-> Ejercicio4.funcRecDouble(t), "ficheros_generados/p1/ej4/funcRecDouble.txt");
		genData(t-> Ejercicio4.funcRecBig(t), "ficheros_generados/p1/ej4/funcRecBig.txt");

		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p1/ej4/funcRecDouble.txt","Recursiva Double");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p1/ej4/funcRecBig.txt","Recursiva Big");
	}

	
}