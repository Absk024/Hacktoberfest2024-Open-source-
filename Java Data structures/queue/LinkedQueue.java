package queue;

public class LinkedQueue implements Queue {

    private int size;  // To keep track of the size of the queue
    private Node head = new Node(null);  // Sentinel head node, initialized as a circular doubly linked list

    // Inner class Node represents an element in the queue
    private class Node {
        private Object object;  // The value of the node (the element stored)
        private Node previous = this;  // Reference to the previous node, initially points to itself
        private Node next = this;      // Reference to the next node, initially points to itself

        public Node(Object obj) {
            this.object = obj;
        }

        public Node(Object object, Node previous, Node next) {
            this.object = object;
            this.previous = previous;
            this.next = next;
        }
    }

    // Returns the first element of the queue without removing it
    @Override
    public Object first() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        return head.next.object;  // The first element is the one right after head
    }

    // Removes and returns the first element from the queue
    @Override
    public Object remove() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        Object firstElement = head.next.object;  // Store the first element to return later
        head.next = head.next.next;              // Adjust the next reference of head
        head.next.previous = head;               // Adjust the previous reference of the new head.next
        size--;                                  // Decrement the size of the queue
        return firstElement;
    }

    // Adds a new element to the end of the queue
    @Override
    public void add(Object obj) {
        // Insert the new element at the tail end
        head.previous = head.previous.next = new Node(obj, head.previous, head);
        size++;  // Increment the size of the queue
    }

    // Returns the current size of the queue
    @Override
    public int size() {
        return size;
    }

    // Checks if the queue is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Main method for testing the queue implementation
    public static void main(String[] args) {
        LinkedQueue carsBought = new LinkedQueue();
        LinkedQueue carsSold = new LinkedQueue();

        // Add car prices to 'carsBought' and 'carsSold' queues
        carsBought.add(1000);
        carsBought.add(2000);
        carsBought.add(500);
        
        carsSold.add(1100);
        carsSold.add(1999);
        carsSold.add(600);

        int totalProfit = 0;  // Initialize total profit
        int size = carsBought.size();  // Get the size of the 'carsBought' queue

        // Calculate profit for each car by comparing bought and sold prices
        for (int i = 0; i < size; i++) {
            totalProfit += (int) carsSold.remove() - (int) carsBought.remove();
        }

        // Output the total profit
        System.out.println("Total Profit On these Three Cars: " + totalProfit);
    }
}
