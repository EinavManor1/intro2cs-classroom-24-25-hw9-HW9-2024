import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a list of Nodes.
 */
public class LinkedList {

<<<<<<< HEAD
    private Node first; // pointer to the first element of this list
    private Node last;  // pointer to the last element of this list
    private int size;   // number of elements in this list

    /**
     * Constructs a new list.
     */
    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public Node getFirst() {
        return this.first;
    }

    public Node getLast() {
        return this.last;
    }

    public int getSize() {
        return this.size;
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

    public void add(int index, MemoryBlock block) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index must be between 0 and size.");
        }

        Node newNode = new Node(block);

        if (index == 0) {
            newNode.next = first;
            first = newNode;
            if (size == 0) last = newNode;
        } else {
            Node prevNode = getNode(index - 1);
            newNode.next = prevNode.next;
            prevNode.next = newNode;
            if (index == size) last = newNode;
        }

        size++;
    }

   
    public void addLast(MemoryBlock block) {
        Node newNode = new Node(block);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    
    public void addFirst(MemoryBlock block) {
        Node newNode = new Node(block);
        if (size == 0) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first = newNode;
        }
        size++;
    }

    /**
     * Adds a new memory block after a specified node.
     *
     * @param afterNode the node after which the new block will be added
     * @param block     the memory block to add
     */
    public void addAfter(Node afterNode, MemoryBlock block) {
        if (afterNode == null) {
            throw new IllegalArgumentException("The specified node cannot be null.");
        }

        Node newNode = new Node(block);
        newNode.next = afterNode.next;
        afterNode.next = newNode;

        if (afterNode == last) {
            last = newNode;
        }

        size++;
    }

    public MemoryBlock getBlock(int index) {
        return getNode(index).block;
    }

    
    public int indexOf(MemoryBlock block) {
        Node current = first;
        int index = 0;

        while (current != null) {
            if (current.block.equals(block)) return index;
            current = current.next;
            index++;
        }

        return -1; 
    }


    public void remove(Node node) {
        
		if (node == null) {
			throw new NullPointerException("NullPointerException!");
		}
	
		if (first == null) {
			throw new IllegalArgumentException("TheList is empty.");
		}
        if (node == first) {
            first = first.next;
            if (size == 1) last = null;
        } else {
            Node prev = first;
            while (prev.next != null && prev.next != node) {
                prev = prev.next;
            }

            if (prev.next == null) {
                throw new IllegalArgumentException("Node not found in the list.");
            }

            prev.next = node.next;
            if (node == last) last = prev;
        }

        size--;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Index out of bounds.");

        if (index == 0) {
            first = first.next;
            if (size == 1) last = null;
        } else {
            Node prev = getNode(index - 1);
            Node removedNode = prev.next;
            prev.next = removedNode.next;
            if (removedNode == last) last = prev;
        }

        size--;
    }

    public void remove(MemoryBlock block) {
		if (block == null) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
	
		if (first == null) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
	
		if (first.block.equals(block)) {
			first = first.next;
			if (size == 1) {
				last = null; 
			}
			size--;
			return;
		}
	
		Node prev = first;
		Node current = first.next;
	
		while (current != null) {
			if (current.block.equals(block)) {
				prev.next = current.next;
				if (current == last) {
					last = prev;
				}
				size--;
				return;
			}
			prev = current;
			current = current.next;
		}
	
		throw new IllegalArgumentException("index must be between 0 and size");
	}

    public ListIterator iterator() {
        return new ListIterator(first);
    }

	public boolean contains(MemoryBlock block) {
    return indexOf(block) != -1;
}
	public void reverse() {
		Node prev = null, current = first, next;
		last = first;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		first = prev;
	}
    @Override
public String toString() {
    StringBuilder result = new StringBuilder();
    Node current = first;

    while (current != null) {
        result.append(current.block.toString());
        if (current.next != null) result.append(" ");
        current = current.next;
    }

    return result.toString();
=======
	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
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
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("Index must be between 0 and size");
		}
	
		Node newNode = new Node(block);
	
		if (index == 0) {
			newNode.next = first;
			first = newNode;
			if (size == 0) last = newNode;
		} else {
			Node prevNode = getNode(index - 1);
			newNode.next = prevNode.next;
			prevNode.next = newNode;
			if (index == size) last = newNode;
		}
	
		this.size++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node newNode = new Node(block);

    if (first == null) { 
        first = newNode;
        last = newNode;
    } else {
        last.next = newNode;
        last = newNode;
    }

    size++;
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node newNode = new Node(block);

		if (size == 0) {
			first = newNode;
			last = newNode;
		} else {
			newNode.next = first;
			first = newNode;
		}
	
		size++;
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		Node node = getNode(index);
		return node.block;
	}

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
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
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		if (node == null) {
			throw new NullPointerException("NullPointerException!");
		}
	
		if (first == null) {
			throw new IllegalArgumentException("The list is empty.");
		}
	
		if (first == node) {
			first = first.next;
			if (size == 1) {
				last = null; 
			}
			size--;
			return;
		}
	
		Node prev = first;
		Node current = first.next;
	
		while (current != null) {
			if (current == node) {
				prev.next = current.next;
				if (current == last) {
					last = prev;
				}
				size--;
				return;
			}
			prev = current;
			current = current.next;
		}
	
		throw new IllegalArgumentException("The given node is not in the list.");
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index out of bounds");
		}
	
		if (index == 0) {
			first = first.next;
			if (size == 1) {
				last = null;
			}
		} else {
			Node prev = getNode(index - 1);
			Node removedNode = prev.next;
			prev.next = removedNode.next;
	
			if (index == size - 1) {
				last = prev;
			}
		}
	
		size--;
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
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
        if (size == 1) {
            last = null; 
        }
        size--;
        return;
    }

    Node prev = first;
    Node current = first.next;

    while (current != null) {
        if (current.block.equals(block)) {
            prev.next = current.next;
            if (current == last) {
                last = prev;
            }
            size--;
            return;
        }
        prev = current;
        current = current.next;
    }

    throw new IllegalArgumentException("index must be between 0 and size");
}

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		if (first == null) {
			return ""; 
		}
	
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
	/**
 * Sorts the linked list using the given comparator.
 *
 * @param comparator a comparator to define the order of the memory blocks
 */
public void sort(Comparator<MemoryBlock> comparator) {
    if (size <= 1) {
        return;
    }

    List<MemoryBlock> blocks = new ArrayList<>();
    Node current = first;
    while (current != null) {
        blocks.add(current.getBlock());
        current = current.getNext();
    }

    // Sort the array
    blocks.sort(comparator);

    // Reconstruct the linked list in sorted order
    first = null;
    last = null;
    size = 0;

    for (MemoryBlock block : blocks) {
        addLast(block);
    }
>>>>>>> 383e4c3073a1ac70b410ddcb7e43194c9b8db380
}
}