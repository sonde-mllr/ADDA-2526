package EjerciciosTeoria;

import java.util.List;

import us.lsi.common.LongPair;

public class TestTema1 {

	public static void main(String[] args) {
		List<LongPair> l1 = Tema1.primosPar(45L, 18492L, 2);
		System.out.println(l1);
		List<LongPair> l2 = Tema1.primosParImperativa(45L, 18492L, 2);
		System.out.println(l2);
		List<LongPair> l3 = Tema1.primosParImperativa2(45L, 18492L, 2);
		System.out.println(l3);
	}

}
