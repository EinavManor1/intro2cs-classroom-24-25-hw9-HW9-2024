/**
 * Represents a managed memory space.
 * Manages allocated and free memory blocks with methods to allocate (`malloc`),
 * free, and defragment the space.
 */
public class MemorySpace {

    // A list of memory blocks that are presently allocated
    private LinkedList allocatedList;

    // A list of memory blocks that are presently free
    private LinkedList freeList;

    /**
     * Constructs a new managed memory space of a given maximal size.
     *
     * @param maxSize the size of the memory space to be managed
     */
    public MemorySpace(int maxSize) {
        // Initializes an empty list of allocated blocks
        allocatedList = new LinkedList();
        // Initializes a free list containing a single block representing the entire memory
        freeList = new LinkedList();
        freeList.addLast(new MemoryBlock(0, maxSize));
    }

    /**
     * Allocates a memory block of a requested length (in words).
     *
     * @param length the length (in words) of the memory block that has to be allocated
     * @return the base address of the allocated block, or -1 if unable to allocate
     */
    public int malloc(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Size must be greater than 0");
		}
	
		for (int i = 0; i < freeList.getSize(); i++) {
			MemoryBlock block = freeList.getBlock(i);
	
			// Check if the block is large enough
			if (block.getLength() >= size) {
				int baseAddress = block.getBaseAddress();
	
				// Adjust the free block or remove it entirely
				if (block.getLength() > size) {
					block.setBaseAddress(baseAddress + size);
					block.setLength(block.getLength() - size);
				} else {
					freeList.remove(i);
				}
	
				allocatedList.add(allocatedList.getSize(), new MemoryBlock(baseAddress, size)); // Provide the index
				return baseAddress; // Return the base address of the allocated block
			}
		}
	
		// No block large enough was found
		return -1;
	}
    /**
     * Frees the memory block whose base address equals the given address.
     *
     * @param baseAddress the starting address of the block to free
     */
    public void free(int baseAddress) {
        if (allocatedList.getSize() == 0) {
            throw new IllegalArgumentException("No blocks allocated to free.");
        }

        MemoryBlock blockToFree = null;

        // Find the block in the allocated list
        for (int i = 0; i < allocatedList.getSize(); i++) {
            MemoryBlock block = allocatedList.getBlock(i);
            if (block.getBaseAddress() == baseAddress) {
                blockToFree = block;
                break;
            }
        }

        if (blockToFree == null) {
            throw new IllegalArgumentException("Address not found in allocated blocks.");
        }

        // Remove from allocated and add to free list
        allocatedList.remove(blockToFree);
        freeList.addLast(blockToFree);
    }

    /**
     * Performs defragmentation of this memory space.
     */
    public void defrag() {
        // Sort the free list by base address
        freeList.sortByBaseAddress();

        for (int i = 0; i < freeList.getSize() - 1; i++) {
            MemoryBlock current = freeList.getBlock(i);
            MemoryBlock next = freeList.getBlock(i + 1);

            // If blocks are contiguous, merge them
            if (current.getBaseAddress() + current.getLength() == next.getBaseAddress()) {
                current.setLength(current.getLength() + next.getLength());
                freeList.remove(i + 1);
                i--; // Recheck the current index with the next block
            }
        }
    }

    /**
     * A textual representation of the free list and the allocated list for debugging purposes.
     */
    @Override
    public String toString() {
        return freeList.toString() + "\n" + allocatedList.toString();
    }
}