/**
 * Represents a node in a linked list. Each node points to a MemoryBlock object. 
 */
public class Node {

	MemoryBlock block;  // The memory block that this node points at
	Node next = null;   // The next node in the list

	/**
	 * Constructs a new node, pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 */
	public Node(MemoryBlock block) {
		this.block = block;
	}
	
	/**
	 * A textual representation of this node, for debugging.
	 * The node's contents, which is a memory block, appears within
	 * curly brackets. For example: {(208,10)}. 
	 */
	public String toString() {
		return "{" + block + "}";
	}
}