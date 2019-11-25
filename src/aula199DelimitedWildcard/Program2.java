package aula199DelimitedWildcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program2 {

	public static void main(String[] args) {
		
		List <Integer> ints = Arrays.asList(12, 15, 68, 98, 2);
		List <Double> dbls = Arrays.asList(1.42, 5.46, 24.8, 3.0, 8.1);
		List <Object> objs = new ArrayList<>();
		
		copyList(ints, objs);
		printlist(objs);
		
		copyList(dbls, objs);
		printlist(objs);
	}

	/**
	 * Didactic generic function to copy a source list to a destination list.
	 * 
	 * @param source A wildcard list declared as {@literal List<? extends Number>}
	 * that represents a list of Number objects or any Subclass objects. It is possible
	 * to access all list member because the compiler can upcast an object to return
	 * with get(index i) method. Besides, it is not possible to add any because the
	 * compiler cannot determine if the object to be add is a proper object of some
	 * subclass of Number. This issue is known as covariance and means it is
	 * possible to read items from the generic Collection, but not write data into it.
	 * 
	 * @param destiny A wildcard list declared as {@literal List<? super Number>}
	 * witch represents a list of Number objects or any Superclass objects.  It is possible
	 * to add objects to list because Number is a subclass of any superclass. It means
	 * the compiler can upcast in runtime to add objects to list. Besides, it is not
	 * possible to access any object of list using method get(index i) because the compiler
	 * cannot downcast the object, what would depend of object type stored in position "i".
	 * This issue is known as contravariance and means it is possible to write data into the
	 * Collection, but not to read items from it.
	 * 
	 * @see <a href="https://stackoverflow.com/questions/1368166/what-is-a-difference-between-super-e-and-extends-e">https://stackoverflow.com/questions/1368166/what-is-a-difference-between-super-e-and-extends-e</a>
	 *
	 * */
	public static void copyList(List<? extends Number> source, List<? super Number> destiny) {
		for (Number n : source) {
			destiny.add(n);
		}
	}
	
	/**
	 * Didactic parametric function to print a generic {@literal List<T>}.
	 *
	 * @param list A List of generic type {@literal <T>}.
	 * 
	 * */
	public static void printlist(List<?> list) {

		if (list == null) {
			System.out.println("null");
		}
		else if (list.size() == 0) {
			System.out.println("[ ]");
		}
		else {

			System.out.print("[");

			for (int i = 0; i < list.size(); i++) {
				if (i > 0) {
					System.out.print(", ");
				}

				System.out.print(list.get(i));
			}

			System.out.println("]");
		}
	}
}
