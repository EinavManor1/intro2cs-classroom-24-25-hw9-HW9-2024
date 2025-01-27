/**
 * Represents a node in a linked list. Each node points to a MemoryBlock object. 
 */
public class Node {

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
	
    public MemoryBlock getBlock() {
        return block;
    }

    public void setBlock(MemoryBlock block) {
        if (block == null) {
            throw new IllegalArgumentException("MemoryBlock cannot be null.");
        }
        this.block = block;
    }

    public Node getNext() {
        return next;
    }
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
    }
}