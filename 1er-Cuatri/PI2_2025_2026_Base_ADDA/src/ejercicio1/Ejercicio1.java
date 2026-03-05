package ejercicio1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.math3.analysis.function.Abs;

import us.lsi.tiposrecursivos.BEmpty;
import us.lsi.tiposrecursivos.BLeaf;
import us.lsi.tiposrecursivos.BTree;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.TEmpty;
import us.lsi.tiposrecursivos.TLeaf;
import us.lsi.tiposrecursivos.TNary;
import us.lsi.tiposrecursivos.Tree;

public class Ejercicio1 {	

/*	


	
	public static Boolean recursivo(BinaryTree<Character> tree, List<Character> ls, int i) {
		Integer n = ls.size();
		return switch (tree) {
		case BEmpty() -> false;
		case BLeaf(var lb) -> n - i == 1 && ls.get(i).equals(lb);
		case BTree(var lb, var lt, var rt) -> n - i > 0 && ls.get(i).equals(lb)
				&& (recursivo(lt, ls, i+1) || recursivo(rt, ls, i+1));
		};
	}

*/	
	
/*
	private static <E> void recursivo(Tree<E> tree, Predicate<E> pred, int nivel, List<Boolean> res) {
		if(res.size() <= nivel) res.add(true);
		switch (tree) {
		case TEmpty() -> {;}
		case TLeaf(var lb) -> {
			Boolean r = pred.test(lb) && res.get(nivel);
			res.set(nivel, r); 
		}
		case TNary(var lb, var chd) -> {
			Boolean r = pred.test(lb) && res.get(nivel);
			res.set(nivel, r);
			chd.forEach(tc -> recursivo(tc, pred, nivel + 1, res));
		}
	}
	
	}	
 * */
	
	private static Integer productoLista(List<Integer> lista) {
		return lista.stream().reduce(1,(a,b) -> a*b);
	}
	
	public static List<Integer> caminoMaximo(BinaryTree<Integer> tree) {
		switch(tree) {
			case BEmpty() -> {return new ArrayList<>();}
			case BLeaf(var lb) -> {
				List<Integer> camino = new ArrayList<>();
				camino.add(lb);
				return camino;
			}
			case BTree(var lb, var lt, var rt) -> {		
				
				List<Integer> izq = caminoMaximo(lt);
				List<Integer> der = caminoMaximo(rt);
				List<Integer> camino = new ArrayList<>();
				camino.add(lb);
				
				if(Math.abs(lb * productoLista(izq) )>Math.abs(lb * productoLista(der))){
					camino.addAll(izq);
				} else {
					camino.addAll(der);
				}
				
				return camino;
			}
		}
		
	}

	public static List<Integer> caminoMaximoTree (Tree<Integer> tree) {
		switch(tree) {
		
		case TEmpty() -> {return new ArrayList<>();}
		case TLeaf(var lb) -> {
			
			List<Integer> camino = new ArrayList<>();
			camino.add(lb);	
			return camino;
			
		}
		
		case TNary(var lb, var chd) -> {
		
			List<Integer> res = new ArrayList<Integer>();
			
			res.add(lb);
			
			res.addAll(chd.stream().map(Ejercicio1::caminoMaximoTree).max(Comparator.comparing(x->Math.abs(lb * productoLista(x)))).orElse(null));
			
			return res;
		}
		
		}
	}
	

}
