package ejercicios;

public record EnteroCadena(Integer a, String s) {

	public static EnteroCadena of(Integer i, String s) {
		return new EnteroCadena(i,s);
	}

}
