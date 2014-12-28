package com.itranswarp.jsonstream;

import java.util.EmptyStackException;

class Stack {

	final int SIZE;
	final StackValue[] array;
	int pos = 0;

	public Stack() {
		this.SIZE = 100;
		this.array = new StackValue[this.SIZE];
	}

	boolean isEmpty() {
		return pos==0;
	}

	void push(StackValue obj) {
		if (pos == SIZE) {
			throw new StackOverflowError("Maximum depth reached when parse JSON string.");
		}
		array[pos] = obj;
		pos ++;
	}

    StackValue pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        pos --;
        return array[pos];
    }

	StackValue pop(int type) {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		pos --;
		StackValue obj = array[pos];
		if (obj.type == type) {
			return obj;
		}
		throw new JsonParseException("Unmatched object or array.");
	}

	int getTopValueType() {
	    StackValue obj = array[pos-1];
	    return obj.type;
	}

	StackValue peek(int type) {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		StackValue obj = array[pos-1];
        if (obj.type == type) {
            return obj;
        }
		throw new JsonParseException("Unmatched object or array.");
	}
}
