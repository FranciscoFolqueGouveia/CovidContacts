package dataStructures;

import exceptions.NoElementException;

/**
 * Doubly linked list Implementation
 * 
 * @author AED Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
public class DoublyLinkedList<E> implements List<E> {

	/**
	 * Serial Version UID of the Class
	 */
	static final long serialVersionUID = 0L;

	static class DListNode<E> {
		// Element stored in the node.
		protected E element;
		// (Pointer to) the next node.
		protected DListNode<E> next;
		// (Pointer to) the previous node.
		protected DListNode<E> previous;

		public DListNode(E elem, DListNode<E> thePrev, DListNode<E> theNext) {
			element = elem;
			previous = thePrev;
			next = theNext;
		}

		public DListNode(E theElement) {
			this(theElement, null, null);
		}

		public E getElement() {
			return element;
		}

		public DListNode<E> getNext() {
			return next;
		}

		public DListNode<E> getPrevious() {
			return previous;
		}

		public void setElement(E newElement) {
			element = newElement;
		}

		public void setNext(DListNode<E> newNext) {
			next = newNext;
		}

		public void setPrevious(DListNode<E> newPrevious) {
			previous = newPrevious;
		}

	}

	// Node at the head of the list.
	protected DListNode<E> head;

	// Node at the tail of the list.
	protected DListNode<E> tail;

	// Number of elements in the list.
	protected int currentSize;

	public DoublyLinkedList() {
		head = null;
		tail = null;
		currentSize = 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO
		return currentSize == 0;
	}

	@Override
	public int size() {
		// TODO
		return currentSize;
	}

	@Override
	public int find(E element) {
		int pos = 0;
		DListNode<E> auxNo;
		boolean found = false;

		auxNo = head;

		while (!found && pos < size()) {
			if (element.equals(auxNo.element)) {
				found = true;
			} else {
				pos++;
				auxNo = auxNo.next;
			}
		}
		if (found) {
			return pos;
		} else
			return -1;
	}

	@Override
	public E getFirst() throws NoElementException {
		if (isEmpty())
			throw new NoElementException();
		return head.getElement();
	}

	@Override
	public E getLast() throws NoElementException {
		if (isEmpty())
			throw new NoElementException();

		return tail.getElement();
	}

	@Override
	public E get(int position) throws InvalidPositionException {
		if (position < 0 || position >= currentSize)
			throw new InvalidPositionException();
		return getNode(position).getElement();
	}

	private DListNode<E> getNode(int position) {
		DListNode<E> auxNode = head;
		for (int i = 1; i <= position; i++) {
			auxNode = auxNode.getNext();
		}
		// avancar na lista usando auxNode
		return auxNode;
	}

	@Override
	public void addFirst(E element) {
		if (isEmpty()) {
			head = new DListNode<E>(element);
		} else if (size() == 1) {
			tail = head;
			head = new DListNode<E>(element, null, tail);
			tail.setPrevious(head);

		}

		else {

			DListNode<E> newNo = new DListNode<E>(element, null, head);
			head = newNo;
		}
		currentSize++;
	}

	@Override
	public void addLast(E element) {
		if (isEmpty()) {
			head = new DListNode<E>(element);
		} else if (size() == 1) {
			tail = new DListNode<E>(element, head, null);

		} else {
			DListNode<E> newNode = new DListNode<E>(element, tail, null);
			tail = newNode;

		}
		currentSize++;
	}

	private void addMiddle(int position, E element) {
		DListNode<E> auxNode = getNode(position);
		DListNode<E> prevNode = auxNode.getPrevious();

		DListNode<E> newNode = new DListNode<E>(element, prevNode, auxNode);
		prevNode.setNext(newNode);
		currentSize++;

	}

	@Override
	public void add(int position, E element) throws InvalidPositionException {
		if (position < 0 || position > currentSize)
			throw new InvalidPositionException();
		if (position == 0)
			addFirst(element);
		else if (position == currentSize)
			addLast(element);
		else {
			addMiddle(position, element);
		}

	}

	/**
	 * Removes the first node in the list. Pre-condition: the list is not empty.
	 */
	private void removeFirstNode() {
		// TODO
		// Cuidado: lista com 1 elemento

		if (size() > 1) {
			head = head.next;
			head.setPrevious(null);
			currentSize--;
		}

		else if (size() == 1) {
			head = null;
			tail = null;
			currentSize--;
		}
	}

	@Override
	public E removeFirst() throws NoElementException {
		if (isEmpty()) {
			throw new NoElementException();
		}

		E element = head.getElement();
		this.removeFirstNode();
		return element;
	}

	/**
	 * Removes the last node in the list. Pre-condition: the list is not empty.
	 */
	private void removeLastNode() {
		// TODO
		// Cuidado: lista com 1 elemento
		DListNode<E> prevNode = tail.getPrevious();
		if (size() > 1) {
			prevNode.setNext(null);
			currentSize--;
		} else if (size() == 1) {
			head = null;
			tail = null;
			currentSize--;
		}
	}

	@Override
	public E removeLast() throws NoElementException {
		if (isEmpty())
			throw new NoElementException();

		E element = tail.getElement();
		this.removeLastNode();
		return element;
	}

	/**
	 * Removes the specified node from the list. Pre-condition: the node is neither
	 * the head nor the tail of the list.
	 * 
	 * @param node - middle node to be removed
	 */
	private void removeMiddleNode(DListNode<E> node) {
		// TODO
		DListNode<E> prevNode = node.previous;
		DListNode<E> nextNode = node.next;

		if (prevNode == null) {
			removeFirstNode();
		} else if (nextNode == null) {
			removeLastNode();
		} else
			prevNode.setNext(nextNode);
		nextNode.setPrevious(prevNode);
		currentSize--;
	}

	private E removeMiddle(int position) {
		DListNode<E> nodeToRemove = this.getNode(position);
		this.removeMiddleNode(nodeToRemove);
		return nodeToRemove.getElement();
	}

	@Override
	public E remove(int position) throws InvalidPositionException {
		if (position < 0 || position >= currentSize)
			throw new InvalidPositionException();
		if (position == 0)
			return removeFirst();
		if (position == currentSize - 1)
			return removeLast();
		return removeMiddle(position);
	}

	@Override
	public boolean remove(E element) {
		// TODO: implementar metodos auxiliares
		DListNode<E> node = this.findNode(element);
		if (node == null) {
			return false;}
		else {
			if (node == head)
				this.removeFirstNode();
			else if (node == tail)
				this.removeLastNode();
			else
				this.removeMiddleNode(node);
			return true;
		}
	}

	private DListNode<E> findNode(E element) {
		// TODO Auto-generated method stub
		int pos = find(element);
		if (pos > -1) {
			return getNode(pos);
		} else
			return null;
	}

	@Override
	public Iterator<E> iterator() {
		return new DoublyLLIterator<E>(head, tail);
	}

	/**
	 * Removes all of the elements from the specified list and inserts them at the
	 * end of the list (in proper sequence).
	 * 
	 * @param list - list to be appended to the end of this
	 */
	public void append(DoublyLinkedList<E> list) {
		// TODO: Left as an exercise.
	}

}
