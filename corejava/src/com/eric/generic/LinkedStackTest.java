package com.eric.generic;

/**
 * 
 * This example makes use of an end sentinel to determine when the stack is empty. The end sentinel is created when the
 * LinkedStack is constructed, and each time you call push( ) a new Node<T> is created and linked to the previous
 * Node<T>. When you call pop( ), you always return the top.item, and then you discard the current Node<T> and move to
 * the next one— except when you hit the end sentinel, in which case you don’t move. That way, if the client keeps
 * calling pop( ), they keep getting null back to indicate that the stack is empty.
 * 
 * 
 * 
 * archive $ProjectName: $
 * 
 * @author Admin
 * 
 * @version $Revision: $ $Name: $
 */

public class LinkedStackTest {
    public static void main(String[] args) {
        /* use string to test stack */
        LinkedStack<String> strList = new LinkedStack<String>();
        strList.test("You can see how the final  specification on the public fields prevents them from being reassigned after construction, in the failure of the statement"
                .split(" "));
        /* use integer to test stack */
        LinkedStack<Integer> intList = new LinkedStack<Integer>();
        intList.test(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 4, 3, 5 });
    }
}

class LinkedStack<T> {
    /* use special type to test push and pop data elements */
    public void test(T[] array) {
        LinkedStack<T> ls = new LinkedStack<T>();
        for (T t : array) {
            ls.push(t);
        }
        while (!ls.end()) {
            System.out.println(ls.pop());
        }

    }

    /* inner class for list node */
    public static class Node<U> {
        U data;
        Node<U> next;

        public Node() {
            this.data = null;
            this.next = null;
        }

        public Node(U data, Node<U> next) {
            this.data = data;
            this.next = next;
        }
    }

    /* stack top */
    private Node<T> head = new Node<T>();

    public void push(T data) {
        head = new Node<T>(data, head);
    }

    public T pop() {
        T data = head.data;
        head = head.next;
        return data;
    }

    /* to check if end of the stack */
    public boolean end() {
        return head.data == null && head.next == null;
    }
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
