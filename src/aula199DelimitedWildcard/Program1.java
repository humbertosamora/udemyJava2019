package aula199DelimitedWildcard;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import aula199DelimitedWildcard.entities.Circle;
import aula199DelimitedWildcard.entities.Rectangle;
import aula199DelimitedWildcard.entities.Shape;

public class Program1 {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(new Circle(2.0));
		shapes.add(new Rectangle(2.0,3.5));
		System.out.printf("Total area: %.2f\n", totalArea(shapes));
		
		List<Circle> circles = new ArrayList<Circle>();
		circles.add(new Circle(4.0));
		circles.add(new Circle(3.0));
		System.out.printf("Total area: %.2f\n", totalArea(circles));
		
	}
	
	/*
	 * List<? extends Shape> represents a generic list of Shape objects
	 * or Shape Subclass objects.
	 */
	public static double totalArea(List<? extends Shape> list) {
		
		double area = 0.0;
		
		for (Shape s : list) {
			area += s.area();
		}
		
		return area;
	}
	
	
}
