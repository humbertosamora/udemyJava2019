package aula023Casting;

public class Main {

	public static void main(String[] args) {
		int a = 5;
		int b = 2;
		
		double c;
		
		c = a / b;
		
		System.out.println("int a = 5;");
		System.out.println("int b = 2;");
		System.out.println("double c;");
		System.out.printf("// Casting implícito de int para double:%n");
		System.out.printf("c = a / b = %.2f%n", c);
		
		c = (double) a / b;
		System.out.printf("// Casting explícito de int para double:%n");
		System.out.printf("c = (double) a / b = %.2f%n", c);

	}

}
