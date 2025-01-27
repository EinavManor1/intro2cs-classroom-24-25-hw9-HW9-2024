/**
 * Represents a node in the linked list, containing a MemoryBlock and a reference to the next node.
 */
public class Node {

    public  MemoryBlock block;
    public  Node next;

    public Node(MemoryBlock block) {
        this.block = block;
        this.next = null;
    }

    public MemoryBlock getBlock() {
        return block;
    }
   
    public void setBlock(MemoryBlock block) {
        this.block = block;
    }

    public Node getNext() {
        return next;
    }

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
    }
}