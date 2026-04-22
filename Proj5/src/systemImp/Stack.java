package systemImp;

/**
 * A class that implements a Stack using your custom Doubly Linked List.  All operations must be O(1).
 */
public class Stack<T extends Comparable<T>> {
    private DoublyLinkedList<T> list; // The doubly linked list that will back the stack.

    public Stack() {
    	list = new DoublyLinkedList<T>();
    }
 
    public void push(T data) {
    	list.addLast(data);
    }
    public T pop() {
    	if (list.getSize() == 0) {
    		throw new IllegalStateException("Stack is empty");
    	}
    	
    	T data = list.getLast();
    	list.removeLast();
    	return data;
    }
    
    public T peek() {
    	if (list.getSize() == 0) {
    		throw new IllegalStateException("Stack is empty");
    	}
    	
    	return list.getLast();
    }
    
    public boolean isEmpty() {
    	return list.getSize() == 0;
    }
    
    public int size() {
    	return list.getSize();
    }
}

