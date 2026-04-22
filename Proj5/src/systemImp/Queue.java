package systemImp;

/**
 * A class that implements a Queue using your custom Doubly Linked List.
 * All operations must be O(1).
 */
public class Queue<T extends Comparable<T>> {
    private DoublyLinkedList<T> list; // The doubly linked list that backs the queue.

    public Queue() {
    	list = new DoublyLinkedList<T>();
    }
    
    public void enqueue(T data) {
    	list.addLast(data);
    }
    
    public T dequeue() {
    	if (list.getSize() == 0) {
    		throw new IllegalStateException("Queue is empty");
    	}
    	T data = list.getFirst();
    	list.removeFirst();
    	return data;
    }
    
    public T peek() {
    	if (list.getSize() == 0) {
    		throw new IllegalStateException("Queue is empty");
    	}
    	return list.getFirst();
    }
    
    public boolean isEmpty() {
    	return list.getSize() == 0;
    }
    
    public int size() {
    	return list.getSize();
    }
}