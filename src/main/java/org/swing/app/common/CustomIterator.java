package org.swing.app.common;

import java.util.Iterator;

public class CustomIterator<E> implements Iterator<E> {

    private final E[] elements;
    private int position;

    public CustomIterator(E[] elements) {
        this.elements = elements;
        this.position = 0;
    }

    public boolean hasNext() {
        return this.position < this.elements.length - 1;
    }

    public E next() {
        final E element = this.elements[this.position];
        this.position++;
        return element;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
