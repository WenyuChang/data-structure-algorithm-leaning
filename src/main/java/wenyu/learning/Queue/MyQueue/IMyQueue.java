package wenyu.learning.Queue.MyQueue;


public interface IMyQueue<E> {
	public boolean offer(E element);
	public E poll();
	public E peek();
	public boolean clear();
	public int size();
	public boolean isEmpty();
}
