import java.util.Comparator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Mergesort algorithm.
 *
 * @author CS221
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		return new IUDoubleLinkedList<T>(); //TODO: replace with your IUDoubleLinkedList for extra-credit
	}
	
	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{
		mergesort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		mergesort(list, c);
	}

	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void mergesort(IndexedUnsortedList<T> list)
	{
		//base case: list iwth 0 or 1 element already sorted
		if (list.size() <= 1) {
			return;
		}
		
		//split list into halves
		IndexedUnsortedList<T> left = newList();
		IndexedUnsortedList<T> right = newList();

		int midpoint = list.size() / 2;

		//move first half to left list
		for (int i = 0; i < midpoint; i++) {
			left.addToRear(list.removeFirst());
		}

		//move second half to right list
		while (!list.isEmpty()) {
			right.addToRear(list.removeFirst());
		}

		//recursively sort both halves
		mergesort(left);
		mergesort(right);

		//merge sorted halves back into og list
		while (!left.isEmpty() && !right.isEmpty()) {
			if (left.first().compareTo(right.first()) <= 0) {
				list.addToRear(left.removeFirst());
			} else {
				list.addToRear(right.removeFirst());
			}
		}

		//add any remaining elements from left / right
		while (!left.isEmpty()) {
			list.addToRear(left.removeFirst());
		}

		while (!right.isEmpty()) {
			list.addToRear(right.removeFirst());
		}
	}

	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void mergesort(IndexedUnsortedList<T> list, Comparator<T> c)
	{
		//base case: list iwth 0 or 1 element already sorted
		if (list.size() <= 1) {
			return;
		}
		
		//split list into halves
		IndexedUnsortedList<T> left = newList();
		IndexedUnsortedList<T> right = newList();

		int midpoint = list.size() / 2;

		//move first half to left list
		for (int i = 0; i < midpoint; i++) {
			left.addToRear(list.removeFirst());
		}

		//move second half to right list
		while (!list.isEmpty()) {
			right.addToRear(list.removeFirst());
		}

		//recursively sort both halves
		mergesort(left,c);
		mergesort(right,c);

		//merge sorted halves back into og list
		while (!left.isEmpty() && !right.isEmpty()) {
			if (c.compare(left.first(), right.first()) <= 0) {
				list.addToRear(left.removeFirst());
			} else {
				list.addToRear(right.removeFirst());
			}
		}

		//add any remaining elements from left / right
		while (!left.isEmpty()) {
			list.addToRear(left.removeFirst());
		}

		while (!right.isEmpty()) {
			list.addToRear(right.removeFirst());
		}
	}
	
}
