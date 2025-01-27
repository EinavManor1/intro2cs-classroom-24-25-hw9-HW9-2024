/**
 * Represents a node in the linked list, containing a MemoryBlock and a reference to the next node.
 */
public class Node {

<<<<<<< HEAD
    public  MemoryBlock block;
    public  Node next;

    public Node(MemoryBlock block) {
        this.block = block;
        this.next = null;
    }

=======
    MemoryBlock block; // The memory block that this node points at
    Node next;         // The next node in the list

    /**
     * Constructs a new node, pointing to the given memory block.
     *
     * @param block the memory block this node will reference
     * @throws IllegalArgumentException if the block is null
     */
    public Node(MemoryBlock block) {
        if (block == null) {
            throw new IllegalArgumentException("MemoryBlock cannot be null.");
        }
        this.block = block;
        this.next = null;
    }
	
>>>>>>> 383e4c3073a1ac70b410ddcb7e43194c9b8db380
    public MemoryBlock getBlock() {
        return block;
    }

    public void setBlock(MemoryBlock block) {
<<<<<<< HEAD
=======
        if (block == null) {
            throw new IllegalArgumentException("MemoryBlock cannot be null.");
        }
>>>>>>> 383e4c3073a1ac70b410ddcb7e43194c9b8db380
        this.block = block;
    }

    public Node getNext() {
        return next;
    }
<<<<<<< HEAD

    public void setNext(Node next) {
        this.next = next;
    }

    public boolean hasNext() {
        return this.next != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return block.equals(node.block);
    }

    @Override
    public String toString() {
        return block.toString();
=======
    public int getBaseAddress() {
        return block.getBaseAddress();
    }
    public int getLength() {
        return block.getLength();
    }

    /**
     * Returns a textual representation of this node, for debugging.
     * Example: {(208,10)}
     *
     * @return a string representation of the node
     */
    @Override
    public String toString() {
        return "{" + block + "}";
>>>>>>> 383e4c3073a1ac70b410ddcb7e43194c9b8db380
    }
}