package stack;

import java.util.NoSuchElementException;

public class ArrayStack implements Stack {

    private int size;           // Current size of the stack (number of elements)
    private Object array[];     // Array to store stack elements

    // Constructor to initialize the stack with a given capacity
    public ArrayStack(int capacity) {
        array = new Object[capacity];
    }

    // Returns the top element without removing it
    @Override
    public Object peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }
        return array[size - 1];  // Top element is the last inserted element
    }

    // Removes and returns the top element from the stack
    @Override
    public Object pop() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }
        Object obj = array[--size];  // Decrease size and remove top element
        array[size] = null;          // Avoid memory leaks by nullifying
        return obj;
    }

    // Pushes a new element onto the stack
    @Override
    public void push(Object obj) {
        if (size == array.length) {  // If array is full, resize it
            resizeArray();
        }
        array[size++] = obj;         // Insert element and increment size
    }

    // Returns the current size of the stack
    @Override
    public int size() {
        return size;
    }

    // Returns true if the stack is empty
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    // Private method to resize the array when it becomes full
    private void resizeArray() {
        Object newArray[] = this.array;   // Temporary array to hold old values
        this.array = new Object[2 * size];  // Double the array capacity
        System.arraycopy(newArray, 0, this.array, 0, newArray.length);  // Copy old values
    }

    // toString() converts all data of the stack into a string representation
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[]";  // Empty stack
        }
        StringBuilder string = new StringBuilder("[");
        for (int i = size - 1; i >= 0; i--) {
            string.append(array[i]).append(", ");  // Append each element
        }
        // Remove last comma and close the string with a bracket
        string.setLength(string.length() - 2);  // Adjust length to remove last ", "
        string.append("]");
        return string.toString();
    }

    // equals() compares two stacks for equality
    public boolean equals(Stack obj) {
        if (this.size() != obj.size()) {
            return false;  // If sizes are not equal, stacks cannot be equal
        }
        Object tempArray1[] = new Object[this.size()];
        Object tempArray2[] = new Object[this.size()];
        boolean areEqual = true;
        int i = 0;

        // Pop elements from both stacks, store them in temporary arrays for comparison
        for (; i < array.length; i++) {
            tempArray1[i] = this.pop();
            tempArray2[i] = obj.pop();
            if (!tempArray1[i].equals(tempArray2[i])) {
                areEqual = false;  // If any element is not equal, stacks are not equal
            }
        }

        // Push elements back into the stacks in their original order
        while (--i >= 0) {
            this.push(tempArray1[i]);
            obj.push(tempArray2[i]);
        }
        return areEqual;
    }

    // findLast() returns the bottom (first) element in the stack
    public Object findLast() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }
        return array[0];  // The first inserted element is at index 0
    }

    // toLinkedStack() converts this ArrayStack into a LinkedStack
    public LinkedStack toLinkedStack() {
        if (this.isEmpty()) {
            return null;
        }
        LinkedStack stack = new LinkedStack();
        for (int i = 0; i < this.size; i++) {
            stack.push(array[i]);
        }
        return stack;
    }

    // Main method to test the stack functionality
    public static void main(String[] args) {
        // Create a stack and push elements
        ArrayStack stack = new ArrayStack(2);
        stack.push(30);
        stack.push("Hello1");
        stack.push(20);

        // Create another stack with different elements
        ArrayStack stack2 = new ArrayStack(2);
        stack2.push(30);
        stack2.push("Hello");
        stack2.push(20);

        // Display stack contents
        System.out.println("stack.toString(): " + stack.toString());
        System.out.println("stack2.toString(): " + stack2.toString());

        // Compare the two stacks
        System.out.println("stack.equals(stack2): " + stack.equals(stack2));

        // Find and display the bottom (first) element
        System.out.println("stack.findLast(): " + stack.findLast());

        // Convert ArrayStack to LinkedStack and display it
        System.out.println("stack.toLinkedStack().toString(): " + stack.toLinkedStack().toString());
    }
}
