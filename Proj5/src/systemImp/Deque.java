package systemImp;

/**
 * A class that implements a Deque (double-ended queue) using a custom Doubly Linked List.
 * All operations (insertion and removal from both ends) must be O(1).
 */
public class Deque<T extends Comparable<T>> {
    private DoublyLinkedList<T> list; // The doubly linked list backing the deque.

    //uses DLL as hidden data type
    public Deque() {
    	list = new DoublyLinkedList<T>();
    }
    
    public void addFirst(T data) {
    	list.addFirst(data);
    }
  
    //adds to tail side
    public void addLast(T data) {
    	list.addLast(data);
    }
    
    public T removeFirst() {
    	if (list.getSize() == 0) {
    		throw new IllegalStateException("Deque is empty");
    	}
    	
    	T data = list.getFirst();
    	list.removeFirst();
    	return data;
    }
    
    //removes from tail side
    public T removeLast() {
    	if (list.getSize() == 0) {
    		throw new IllegalStateException("Deque is empty");
    	}
    	
    	T data = list.getLast();
    	list.removeLast();
    	return data;
    }
    
    //returns first element
    public T peekFirst() {
    	if (list.getSize() == 0) {
    		throw new IllegalStateException("Deque is empty");
    	}
    	return list.getFirst();
    }
    
    public T peekLast() {
    	if (list.getSize() == 0) {
    		throw new IllegalStateException("Deque is empty");
    	}
    	return list.getLast();
    }
    
    public boolean isEmpty() {
    	return list.getSize() == 0;
    }
   
    //size of list
    public int size() {
    	return list.getSize();
    }
}