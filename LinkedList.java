import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents a list of Nodes.
 */
public class LinkedList {

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
	 public void sortByBaseAddress() {
        if (first == null || first.next == null) {
            return; // Already sorted
        }

        // Convert LinkedList to ArrayList for easier sorting
        ArrayList<MemoryBlock> blocks = new ArrayList<>();
        Node current = first;
        while (current != null) {
            blocks.add(current.block);
            current = current.next;
        }

        // Sort using Java's sort
        blocks.sort(Comparator.comparingInt(MemoryBlock::getBaseAddress));

        // Rebuild the linked list from the sorted blocks
        first = null;
        size = 0;
        for (MemoryBlock block : blocks) {
            addLast(block);
        }
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
	}
}