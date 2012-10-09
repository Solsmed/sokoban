import java.util.*;

public class PriorityFIFOQueue<T> implements Queue {

	List<T> queue;
	Comparator<T> comparator;
	
	public PriorityFIFOQueue(Comparator<T> comparator) {
		this.comparator = comparator;
		
		queue = new LinkedList<T>();
	}

	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object poll() {
		if(queue.isEmpty())
			return null;
		Object o = queue.get(0);
		
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
	public boolean add(Object arg0) {
		int index = 0;
		outer: for(index = queue.size() - 1; index>=0; index--){
			int compare = comparator.compare((T) arg0, queue.get(index));
			if(compare==0){
				while(compare==0 && index>=0){
					index--;
					compare = comparator.compare((T) arg0, queue.get(index));
				}
				break outer;								
			}
		}
		//loop through queue from the end
			//compare element in queue with argument
		queue.add(index, (T) arg0);

		return false;
	}
	
	
	/*
	 * 
	 * NOT USED
	 * 
	 */
	
	
	
	@Override
	public boolean addAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean offer(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove() {
		// TODO Auto-generated method stub
		return null;
	}
}
