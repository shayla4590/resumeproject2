package systemImp;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly linked list implementation for storing elements.
 * This list supports basic operations like adding and removing elements at both ends.
 * @class Inner class description.
 * @param <E> The type of elements in this list, which must extend Comparable.
 */
public class DoublyLinkedList<E extends Comparable<E>> implements Iterable<E> {
    private Node head; //points to the first node
    private Node tail; //points to the last node
    private int size;  //number of elements in the list

    /**
     * Node represents a single element in the doubly linked list.
     * It contains references to the data and the next and previous nodes in the list.
     */
    private class Node {
        E data;
        Node next, prev;

        /**
         * Constructor for creating a new node.
         * 
         * @param data The data to be stored in the node.
         */
        Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    public DoublyLinkedList() {
    	head = null;
    	tail = null;
    	size = 0;
    }
    
    public int getSize() {
    	return size;
    }
    
    public E getFirst() {
    	if (size == 0) {
    		throw new NoSuchElementException("List is empty!");
    	}
    	return head.data;
    }
    
    public E getLast() {
    	if (size == 0) {
    		throw new NoSuchElementException("List is empty!");
    	}
    	return tail.data;
    }
    
    public void addFirst(E e) {
    	if (e == null) {
    		throw new NullPointerException("Element cannot be null");    		
    	}
		Node first = new Node(e);
		if (size == 0) {
			head = first;
			tail = first;
		}
		else {
			//updates pointers
			first.next = head;
			head.prev = first;
			head = first;	
		}
		size++;
    }
    
    public void addLast(E e) {
    	if (e == null) {
    		throw new NullPointerException("Element cannot be null");    		
    	}
    	Node last = new Node(e);
    	if (size == 0) {
    		tail = last;
    		head = last;
    	}
    	else {
    	tail.next = last;
    	last.prev = tail;
    	tail = last;
    }
    	size++;
    }

    public void removeFirst() {
    	if (size == 0) {
    		throw new NoSuchElementException("List is empty!");
    	}
    	else if (size == 1) {
    		tail = null;
    		head = null;
    		size = 0;
    	}
    	else {
    	head = head.next;
    	head.prev = null;
    	size--;
    }
    }
    
    public void removeLast() {
    	if (size == 0) {
    		throw new NoSuchElementException("List is empty!");
    	}
    	else if (size == 1) {
    		tail = null;
    		head = null;
    		size = 0;
    	}
    	else {
    	tail = tail.prev;
    	tail.next = null;
    	size--;
    }
    }
    
    @Override
    public String toString() {
    	if (size == 0) {
    		return "[]";
    	}
    	
        Node current = head;
    	StringBuilder output = new StringBuilder("[" + current.data);
    	current = current.next;

    	//loops through adding commas
        while (current != null) {
            output.append(", " + current.data);
            current = current.next;
        }
        output.append("]");
        return output.toString();
    }
    
    
    public void removeAllInRange(E start, E end) {
    	if (start == null || end == null) {
    		throw new NullPointerException("Null endpoints not allowed");
    	}
    	if (start.compareTo(end) > 0) {
    		throw new NoSuchElementException("Invalid range");
    	}
    	
    	int count = 0;
    	Node current = head;
    	while (current != null) {
    		Node next = current.next;
    		
    		//counts how many to update size
    		if (current.data.compareTo(start) >= 0 && current.data.compareTo(end) <= 0) {
    			count++;
    			
    			if (current.prev != null) {
    				current.prev.next = current.next;
    			}
    			else {
    				head = current.next;
    			}
    			if (current.next != null) {
    				current.next.prev = current.prev;
    			}
    			else {
    				tail = current.prev;
    			}
    		}
    		current = next;
    	}
    	
    	size = size - count;
    	
    	if (count == 0) {
    		throw new NoSuchElementException("No elements found in given range");
    	}
    }
    
    public Iterator<E> iterator() {
    	return new Iterator<E>() {
    		Node current = head;
    		boolean canRemove = false;
    		Node lastReturned = null;
    		
    		
    		public boolean hasNext() {
    			return current != null;
    		}
    		
    		public E next() {
    			if (hasNext() == false) {
    				throw new NoSuchElementException("No next exists");
    			}
    			E output = current.data;
    			lastReturned = current;
    			current = current.next;
    			canRemove = true;
    			return output;
    		}
    		
    		public void remove() {
    			if (!canRemove) {
    				throw new IllegalStateException("Next has not been called");
    			}
    			else {
    				Node e = lastReturned;
    				if (e.prev != null) {
            		e.prev.next = e.next;
    				}
    				else {
    					head = e.next;
    				}
    				
    				if (e.next != null) {
            		e.next.prev = e.prev;
    				}
    				else {
    					tail = e.prev;
    				}
    				lastReturned = null;
    			    canRemove = false;
        			size--;
    			}
    		}
    	};
    }
    
     public String insertionSort() {
    	if (size <= 1) {
    		return toString();
    	}
    	
	    Node currentLog = head;
    	int i = 0;
    	int sortedSize = 1;
    	StringBuilder log = new StringBuilder();
    	
    	
    	while (currentLog != null) {
            if (i == sortedSize) {
            	log.append("| ");
            }
            log.append(currentLog.data + " ");
            currentLog = currentLog.next;
            i++;
        }
    	
    	//if fully sorted
    	if (i == sortedSize) {
    		log.append("| ");
    	}
        log.append("\n");

        //keeps track of sorted portion
    	Node sortEnd = head;
        Node current = head.next;

    		while (current != null) {
    		Node next = current.next;
    		
    		if (current.data.compareTo(sortEnd.data) >= 0) {
                sortEnd = current;
            }
    		//updates pointers
    		else {
                sortEnd.next = next;
                if (next != null) {
                    next.prev = sortEnd;
                }

    			if (current.prev != null) {
    			    current.prev.next = current.next;
    			} 

    			if (current.next != null) {
    			    current.next.prev = current.prev;
    			} 
    			current.prev = null;
    			current.next = null;
    	    	
    			Node currentSorted = head;

    		
    		while (currentSorted != null && current.data.compareTo(currentSorted.data) > 0) {
    			currentSorted = currentSorted.next;
    			}
    		
    		current.next = currentSorted;
		    current.prev = currentSorted.prev;
		    
    		if (currentSorted.prev == null) {
    		    current.next = head;
    		    head.prev = current;
    			head = current;
    		}
    		else {
    			current.prev = currentSorted.prev;
    			current.next = currentSorted;
    		    currentSorted.prev.next = current;
    		    currentSorted.prev = current;
    		}
    		}
        		
    		sortedSize++;
    		currentLog = head;
    		i = 0;
    		
    		//logging function
    		while (currentLog != null) {
    			if (i == sortedSize) {
    				log.append("| ");
    			}
        			log.append(currentLog.data + " ");
        			currentLog = currentLog.next;
        			i++;
        		}
    		
    		if (i == sortedSize) {
    			log.append("| ");
    		}
    		
    		log.append("\n");
    		current = next;
    		}
    		
    		//checks correct tail pointer after iteration
    		Node t = head;
        	tail = null;

        	while (t.next != null) {
        	    t = t.next;
        	}
        	tail = t;
        	
    	return log.toString();
}
}