

/**
 * Represents a linked list of Node objects, managing MemoryBlock instances.
 */
public class LinkedList {
    
    private Node first; // Pointer to the first node of the list
    private Node last;  // Pointer to the last node of the list
    private int size;   // Number of nodes in the list

    /**
     * Constructs an empty linked list.
     */
    public LinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * Returns the first node in the list.
     * @return the first node, or null if the list is empty.
     */
    public Node getFirst() {
        return this.first;
    }

    /**
     * Returns the last node in the list.
     * @return the last node, or null if the list is empty.
     */
    public Node getLast() {
        return this.last;
    }

    /**
     * Returns the number of nodes in the list.
     * @return the size of the list.
     */
    public int getSize() {
        return this.size;
    }
	public MemoryBlock[] toArray() {
		MemoryBlock[] array = new MemoryBlock[size];
		Node current = first;
		for (int i = 0; i < size; i++) {
			array[i] = current.block;
			current = current.next;
		}
		return array;
	}
    /**
     * Adds a new MemoryBlock at the specified index.
     * @param index the position where the block should be inserted.
     * @param block the MemoryBlock to be added.
     * @throws IllegalArgumentException if the index is out of bounds.
     */
    public void add(int index, MemoryBlock block) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index must be between 0 and size");
        }

        Node newNode = new Node(block);

        if (index == 0) {
            newNode.next = first;
            first = newNode;
            if (size == 0) last = newNode;
        } else if (index == size) {
            last.next = newNode;
            last = newNode;
        } else {
            Node prev = getNode(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }

        size++;
    }

    /**
     * Adds a MemoryBlock to the end of the list.
     * @param block the MemoryBlock to be added.
     */
    public void addLast(MemoryBlock block) {
        add(size, block);
    }

    /**
     * Adds a MemoryBlock to the beginning of the list.
     * @param block the MemoryBlock to be added.
     */
    public void addFirst(MemoryBlock block) {
        add(0, block);
    }

    /**
     * Retrieves the MemoryBlock at the specified index.
     * @param index the position of the block to retrieve.
     * @return the MemoryBlock at the given index.
     * @throws IllegalArgumentException if the index is out of bounds.
     */
    public MemoryBlock getBlock(int index) {
        return getNode(index).block;
    }

    /**
     * Finds the index of a MemoryBlock in the list.
     * @param block the MemoryBlock to locate.
     * @return the index of the block, or -1 if not found.
     */
    public int indexOf(MemoryBlock block) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (current.block.equals(block)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    /**
     * Removes the node at the specified index.
     * @param index the position of the node to remove.
     * @throws IllegalArgumentException if the index is out of bounds.
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index must be between 0 and size");
        }

        if (index == 0) {
            first = first.next;
            if (size == 1) last = null;
        } else {
            Node prev = getNode(index - 1);
            prev.next = prev.next.next;
            if (index == size - 1) last = prev;
        }

        size--;
    }

    /**
     * Removes the first node pointing to the given MemoryBlock.
     * @param block the MemoryBlock to remove.
     * @throws IllegalArgumentException if the block is not found in the list.
     */
    public void remove(MemoryBlock block) {
		if (block == null) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
	
		if (first == null) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
	
		if (first.block.equals(block)) {
			first = first.next;
			if (size == 1) last = null;
			size--;
			return;
		}
	
		Node prev = first;
		Node current = first.next;
	
		while (current != null) {
			if (current.block.equals(block)) {
				prev.next = current.next;
				if (current == last) last = prev;
				size--;
				return;
			}
			prev = current;
			current = current.next;
		}
	
		throw new IllegalArgumentException("index must be between 0 and size");
	}

    /**
     * Retrieves the node at the specified index.
     * @param index the position of the node to retrieve.
     * @return the Node at the given index.
     * @throws IllegalArgumentException if the index is out of bounds.
     */
	public void remove(Node node) {
		if (node == null) {
			throw new NullPointerException("index must be between 0 and size");
		}
	
		if (first == null) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
	
		if (first == node) {
			first = first.next;
			if (size == 1) last = null;
			size--;
			return;
		}
	
		Node current = first;
		while (current.next != null) {
			if (current.next == node) {
				current.next = current.next.next;
				if (current.next == null) last = current;
				size--;
				return;
			}
			current = current.next;
		}
	
		throw new IllegalArgumentException("index must be between 0 and size");
	}
	 public ListIterator iterator() {
		return new ListIterator(first);
	}
	 public Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index must be between 0 and size");
        }
		
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Returns a textual representation of the list.
     * @return a string representation of the list.
     */

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node current = first;
		while (current != null) {
			result.append(current.block.toString());
			if (current.next != null) {
				 result.append(" ");
			}
			current = current.next;
		}
		return result.toString();
	}
	public void mergeAdjacentBlocks() {
		if (size <= 1) return;

		Node current = first;

		while (current != null && current.next != null) {
			MemoryBlock currentBlock = current.block;
			MemoryBlock nextBlock = current.next.block;

			if (currentBlock.baseAddress + currentBlock.length == nextBlock.baseAddress) {
		
				currentBlock.length += nextBlock.length;
				current.next = current.next.next;
				if (current.next == null) last = current;
				size--;
			} else {
				current = current.next;
			}
		}
	}
	
	public void sortByBaseAddress() {
		if (size <= 1) return;
	
		boolean swapped;
		do {
			swapped = false;
			Node current = first;
			while (current != null && current.next != null) {
				if (current.block.baseAddress > current.next.block.baseAddress) {
					MemoryBlock temp = current.block;
					current.block = current.next.block;
					current.next.block = temp;
					swapped = true;
				}
				current = current.next;
			}
		} while (swapped);
	}
}
