package ejercicio2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import us.lsi.tiposrecursivos.BEmpty;
import us.lsi.tiposrecursivos.BLeaf;
import us.lsi.tiposrecursivos.BTree;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

/*
 
 2. Dado un árbol binario de cadena de caracteres, diseñe un algoritmo que devuelva cierto
	si se cumple que, para todo nodo, el número total de vocales (incluyendo las repetidas)
	contenidas en el subárbol izquierdo es igual al del subárbol derecho
 
 * */

public class Ejercicio2 {
	
	private static Set<Character> vocales = Set.of('A','a','E','e','I','i','O','o','U','u');
	
	public static Boolean solucion_recursiva(BinaryTree<String> tree) {
		return solucion_recursiva_priv(tree) == 1 ? true : false;
	}
	
	private static Integer solucion_recursiva_priv(BinaryTree<String> tree) {
		
		switch(tree) {
			case BEmpty() -> {return 0;}
			case BLeaf(String label) -> {
				Integer aux = 0;
				for (Character c : label.toCharArray()) {
					if(vocales.contains(c)) {
						aux += 1;
					}
				}
				return aux;
				
			}
			case BTree(var label, var left, var right) -> {
				
				Integer izq = solucion_recursiva_priv(left);
				Integer der = solucion_recursiva_priv(right);
				
				return izq == der ? 1:0;
			}
				
		} 
		
	}

	public static Boolean solucion_recursiva(Tree<String> tree) {
		return null;
	}
	
}
