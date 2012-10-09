import java.util.*;

public class PriorityFIFOQueue<T> implements Queue<T> {

	List<T> queue;
	Comparator<T> comparator;
	
	public PriorityFIFOQueue(Comparator<T> comparator) {
		this.comparator = comparator;
		
		queue = new LinkedList<T>();
	}

	
	@Override
	public void clear() {
		queue.clear();
	}

	@Override
	public T poll() {
		if(queue.isEmpty())
			return null;
		T o = queue.get(0);
		
		return o;
	}
	
	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	

	@Override
	public int size() {
		return queue.size();
	}
	

	@Override
	public boolean add(T arg0) {
		int index = 0;
		outer: for(index = queue.size() - 1; index>=0; index--){
			int compare = comparator.compare(arg0, queue.get(index));
			if(compare==0){
				while(compare==0 && index>=0){
					index--;
					compare = comparator.compare(arg0, queue.get(index));
				}
				break outer;								
			}
		}
		//loop through queue from the end
			//compare element in queue with argument
		queue.add(index, arg0);

		return false;
	}

	/*
	 * 
	 * NOT USED
	 * 
	 */	
	
	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public T element() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean offer(T arg0) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public T remove() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
