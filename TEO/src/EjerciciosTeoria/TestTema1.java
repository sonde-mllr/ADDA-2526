package EjerciciosTeoria;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import us.lsi.common.LongPair;
import us.lsi.common.Multiset;
import us.lsi.streams.Collectors2;

public class TestTema1 {

	public static void main(String[] args) {
		/*List<LongPair> l1 = Tema1.primosPar(45L, 18492L, 2);
		System.out.println(l1);
		List<LongPair> l2 = Tema1.primosParImperativa(45L, 18492L, 2);
		System.out.println(l2);
		List<LongPair> l3 = Tema1.primosParImperativa2(45L, 18492L, 2);
		System.out.println(l3);*/
		
		// DIAPOSITIVA 56
		Long m1 = 2L;
		Long n1 = 60L;
		Multiset<Long> rr4 = Stream.iterate(m1,x->x<n1,x->x+1)
				.flatMap(x->Tema1.divisores(x))
				.collect(Collectors2.toMultiset());

		System.out.println(rr4);
		// Resultado : {2:28,3:17,4:11,5:7,6:4,7:2}
		Map<Long,Integer> rr5 = Tema1.divisoresIterativo(2L, 60L);
		System.out.println(rr5);
	}

}
