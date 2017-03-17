package wenyu.learning.Queue;

import java.util.Iterator;
import java.util.Queue;

public class QueueDemoUtils {
	public static <E> void printQueue(Queue<E> queue) {
		Iterator<E> it = queue.iterator();
		StringBuilder printOut = new StringBuilder("Queue: [");
		while(it.hasNext()) {
			printOut.append(it.next().toString()+", ");
		}
		
		if(printOut.length()>8) {
			System.out.println(printOut.substring(0, printOut.length()-2) + "]");
		} else {
			System.out.println(printOut.toString() + "]");
		}
	}
}
