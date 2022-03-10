package dataStructures;

import exceptions.NoElementException;

/**
 * Singly linked list Implementation
 * 
 * @author AED Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
public class SinglyLinkedList<E> implements List<E> {

	/**
	 * Serial Version UID of the Class
	 */
	static final long serialVersionUID = 0L;

	static class SListNode<E> {
		// Element stored in the node.
		protected E element;
		// (Pointer to) the next node.
		protected SListNode<E> next;

		public SListNode(E elem, SListNode<E> theNext) {
			element = elem;
			next = theNext;
		}

		public SListNode(E theElement) {
			this(theElement, null);
		}

		public E getElement() {
			return element;
		}

		public SListNode<E> getNext() {
			return next;
		}

		public void setElement(E newElement) {
			element = newElement;
		}

		public void setNext(SListNode<E> newNext) {
			next = newNext;
		}
		
		/**
		 * @return the element of the node next to the node we have
		 */
		private E getNextElement() {
			return getNext().getElement();
		}

	}

	// Node at the head of the list.
	protected SListNode<E> head;

	// Node at the tail of the list.
	protected SListNode<E> tail;

	// Number of elements in the list.
	protected int currentSize;

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.currentSize == 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.currentSize;
	}

	@Override
	public Iterator<E> iterator() throws NoElementException {
		return new SinglyLLIterator<E>(head);
	}

	@Override
	public int find(E element) {

		int pos = 0;
		SListNode<E> auxNo = head;
		
		boolean found = false;

		while (!found && pos < size()) {
		 if (element.equals(auxNo.getElement())) {
				found = true;
			} else {
				pos++;
				auxNo = auxNo.getNext();
			}
		}
		if (found) {
			return pos;
		} else
			return -1;
	}

	@Override
	public E getFirst() throws NoElementException {
		if (isEmpty()) {
			throw new NoElementException();

		}
		return head.getElement();
	}

	@Override
	public E getLast() throws NoElementException {
		// TODO Auto-generated method stub

		if (isEmpty()) {
			throw new NoElementException();

		}
		return tail.getElement();
	}

	@Override
	public E get(int position) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (position < 0) {
			throw new InvalidPositionException();
		}
		if (position == 0) {
			return getFirst();
		}

		if (position == size() - 1) {
			return getLast();
		}

		SListNode<E> auxNo = head;

		while (position > 0) {
			position--;
			auxNo = auxNo.getNext();
		}
		return auxNo.getElement();
	}

	@Override
	public void addFirst(E element) {

		if (isEmpty()) {
			head = new SListNode<E>(element);

		}

		else if (size() == 1) {
			SListNode<E> newNo = new SListNode<E>(element, head);
			tail = head;
			head = newNo;
		} else {

			SListNode<E> newNo = new SListNode<E>(element, head);
			head = newNo;
		}

		currentSize++;
	}

	@Override
	public void addLast(E element) {
		if (isEmpty()) {
			head = new SListNode<E>(element);
		}

		else if (size() == 1) {
			tail = new SListNode<E>(element);
			head.setNext(tail);

		}

		else if (size() > 1) {
			SListNode<E> newNode = new SListNode<E>(element);
			tail.setNext(newNode);
			tail = newNode;

		}
		currentSize++;

	}

	@Override
	public void add(int position, E element) throws InvalidPositionException {

		if (position < 0 || position > currentSize) {
			throw new InvalidPositionException();
		}
		SListNode<E> auxNo = head;
		if (position == 0) {
			addFirst(element);
		} else if (position == size()) {
			addLast(element);
		} else {

			int positionBefore = position -1;
			for (int count = 0; count != positionBefore; count++) {

				auxNo = auxNo.next;
			}

			SListNode<E> newNo = new SListNode<E>(element, auxNo.next);
			auxNo.setNext(newNo);
			currentSize++;

		}
		

	}

	@Override
	public E removeFirst() throws NoElementException {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new NoElementException();
		}
		E e = head.element;
		head = head.next;
		currentSize--;
		return e;
	}

	@Override
	public E removeLast() throws NoElementException {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new NoElementException();
		}

		E element;
		SListNode<E> auxNo = head;
		if (currentSize == 1) {
			element = getFirst();
			head = null;
			tail = null;
			currentSize--;
			

		} else {
			int penultimate = size() -2;
			for (int count = 0; count != penultimate; count++) {

				auxNo = auxNo.next;

			}
			element = auxNo.getNextElement();
			auxNo.setNext(null);
			tail = auxNo;
			currentSize--;

		}
		return element;
	}

	@Override
	public E remove(int position) throws InvalidPositionException {
		if (position < 0 || position >= currentSize) {
			throw new InvalidPositionException();
		}

		E element;
		SListNode<E> auxNo = head;
		if (position == 0) {
			element = getFirst();
			removeFirst();
		} else if (position == size() - 1) {
			element = getLast();
			removeLast();
		} else {

			int positionBefore = position -1;
			for (int count = 0; count != positionBefore; count++) {

				auxNo = auxNo.getNext();
			}
			element = auxNo.getNextElement();
			auxNo.setNext(auxNo.next.next);
			currentSize--;

		}
	

		return element;
	}

	@Override
	public boolean remove(E element) {

		int position = find(element);
		boolean found = true;

		if (position < 0) {
			found = false;
		}

		else {
			remove(position);
		}
	
		return found;
	}
}
