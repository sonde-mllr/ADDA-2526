package EjerciciosTeoria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import us.lsi.common.LongPair;
import us.lsi.common.Pair;
import us.lsi.math.Math2;
import us.lsi.streams.Stream2;

public class Tema1 {
	
	
	// Diapositiva 55 ======================================================
	/*
	Obtener una lista de pares de un primo y su siguiente, tal que
	el primero sea mayor o igual que m y menor que n, y que la diferencia
	entre ellos sea una cantidad k>=2 dada
	*/
	public static List<LongPair> primosPar(Long m, Long n, Integer k){
		Stream<Long> r = Stream.iterate(Math2.siguientePrimo(m-1),x->x < n , x -> Math2.siguientePrimo(x));
		List<LongPair> r2 = Stream2.consecutivePairs(r)
				.map(p->LongPair.of(p))
				.filter(t->t.second()-t.first() == k)
				.toList();
		return r2;
	}
	
	// Version Imperativa :
	
	// Encontrar secuencia y acumulador
	// Siguiente primo me devuelve el siguiente numero primo del dado por parametro
	// En la versión funcional El iterate busca el siguiente primo del valor sobre el que itera del stream (x)
	// Dando como valor inicial de cada iteracion m-1 -> m será el acumulador, n es la secuencia ?? 
	
	
	// CREO QUE NO ES VALIDO
	public static List<LongPair> primosParImperativa(Long m, Long n, Integer k){

		// Podría añadir una excepcion de si k es menor que 2
		//pero como no lo hace en la version funcional pues no lo hacemos aquí
		List<LongPair> listaPares = new ArrayList<LongPair>();
		
		LongStream rangoNumeros = LongStream.range(m, n); // Rango de numeros de m a n -> ej: [10,42)
		for(Long numero:rangoNumeros.toArray()) { // A cada numero del rango
			if(Math2.esPrimo(numero)) { // Si es primo
				LongPair par = LongPair.of(Pair.of(numero,Math2.siguientePrimo(numero)));// Crea el par con el siguiente primo
				if(par.second()-par.first() == k) { // Si la distancia entre los numeros es k
					listaPares.add(par);
				}
			}
		}
		return listaPares;		
	}
	// OTRA OPCION - GPT (ENTIENDO COMO FUNCIONA)
	public static List<LongPair> primosParImperativa2(Long m, Long n, Integer k){

        List<LongPair> listaPares = new ArrayList<>(); // ACUMULADOR

        // Comenzamos desde el primer primo mayor o igual a m
        long primoActual = Math2.siguientePrimo(m); // SECUENCIA

        while (primoActual < n) {
            long siguientePrimo = Math2.siguientePrimo(primoActual); // e-> nx(e)
            if (siguientePrimo - primoActual == k) {
                listaPares.add(LongPair.of(Pair.of(primoActual, siguientePrimo)));
            }
            primoActual = siguientePrimo; // Avanzamos al siguiente primo
        }

        return listaPares;
    }
	
	// Diferentes entre el mio y el de gpt. En el mio se avanza automáticamente al siguiente posible primo.
	// El mio es peor porque de base no sabe si es primo o no sino que tiene que hacer la comprobación dentro del rango
	// Para mejorarlo se podría filtrar el rango antes de recorrerlo para que solo tenga numeros primos
	
}
