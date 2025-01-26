/**
 * Represents a managed memory space. The memory space manages two lists:
 * one for allocated memory blocks and another for free memory blocks.
 */
public class MemorySpace {

    private LinkedList allocatedList; // List of allocated memory blocks
    private LinkedList freeList;      // List of free memory blocks

    /**
     * Constructs a new managed memory space with a given maximum size.
     * @param maxSize the size of the memory space to manage.
     */
    public MemorySpace(int maxSize) {
		allocatedList = new LinkedList();
		freeList = new LinkedList();
		freeList.addLast(new MemoryBlock(0, maxSize));
	}
    /**
     * Allocates a memory block of a requested length.
     * @param length the size of the memory block to allocate.
     * @return the base address of the allocated block, or -1 if allocation fails.
     */
    public int malloc(int length) {
		if (length <= 0) {
            throw new IllegalArgumentException("Requested length must be positive.");
        }

        Node current = freeList.getFirst();
        while (current != null) {
            MemoryBlock freeBlock = current.block;

            if (freeBlock.length >= length) {
                int baseAddress = freeBlock.baseAddress;

                if (freeBlock.length == length) {
                    freeList.remove(current);
                } else {
                    freeBlock.baseAddress += length;
                    freeBlock.length -= length;
                }

                allocatedList.addLast(new MemoryBlock(baseAddress, length));
                return baseAddress;
            }
            current = current.next;
        }
		return -1; 
	}

    /**
     * Frees a previously allocated memory block.
     * @param baseAddress the base address of the block to free.
     * @throws IllegalArgumentException if the block is not found in allocatedList.
     */
    public void free(int baseAddress) {
		for (int i = 0; i < allocatedList.getSize(); i++) {
            MemoryBlock allocatedBlock = allocatedList.getBlock(i);
            if (allocatedBlock.baseAddress == baseAddress) {
                allocatedList.remove(i);
                freeList.addLast(allocatedBlock);
                return;
            }
        }
        throw new IllegalArgumentException("Block with base address " + baseAddress + " not found.");
    }

	

    /**
     * Defragments the free list by merging adjacent blocks.
     */
    public void defrag() {
		if (freeList.getSize() <= 1) return;

        freeList.sortByBaseAddress();

        Node current = freeList.getFirst();
        while (current != null && current.next != null) {
            MemoryBlock currentBlock = current.block;
            MemoryBlock nextBlock = current.next.block;

            if (currentBlock.baseAddress + currentBlock.length == nextBlock.baseAddress) {
                currentBlock.length += nextBlock.length;
                freeList.remove(current.next);
            } else {
                current = current.next;
            }
        }
    }
    /**
     * Returns a textual representation of the memory space, showing the free and allocated lists.
     * @return a string representation of the memory space.
     */
	
	@Override
	public String toString() {
		return freeList.toString() + "\n" + allocatedList.toString();
	}
}