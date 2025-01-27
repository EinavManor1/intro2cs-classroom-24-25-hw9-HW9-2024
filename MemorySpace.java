public class MemorySpace {

    LinkedList allocatedList;
    LinkedList freeList;

    /**
     * Constructs a new managed memory space of a given maximal size.
     * 
     * @param maxSize The size of the memory space to be managed.
     */
    public MemorySpace(int maxSize) {
        allocatedList = new LinkedList();
        freeList = new LinkedList();
        freeList.addLast(new MemoryBlock(0, maxSize));
    }

    /**
     * Allocates a memory block of a requested length (in words). Returns the base
     * address of the allocated block, or -1 if unable to allocate.
     * 
     * @param length The length (in words) of the memory block to allocate.
     * @return The base address of the allocated block, or -1 if unable to allocate.
     */
    public int malloc(int length) {     
		if (length <= 0) return -1;
	
		MemoryBlock selectedMemoryBlock = null;
		int smallestBlockIndex = -1;
	
		for (int i = 0; i < freeList.getSize(); i++) {
			MemoryBlock block = freeList.getBlock(i);
			if (block.getLength() >= length) {
				if (selectedMemoryBlock == null || block.getLength() < selectedMemoryBlock.getLength()) {
					selectedMemoryBlock = block;
					smallestBlockIndex = i;
				}
			}
		}
	
		if (selectedMemoryBlock == null) {
			defrag();
	
			for (int i = 0; i < freeList.getSize(); i++) {
				MemoryBlock block = freeList.getBlock(i);
				if (block.getLength() >= length) {
					if (selectedMemoryBlock == null || block.getLength() < selectedMemoryBlock.getLength()) {
						selectedMemoryBlock = block;
						smallestBlockIndex = i;
					}
				}
			}
	
			if (selectedMemoryBlock == null) {
				return -1; 
			}
		}
	
		int baseAddress = selectedMemoryBlock.getBaseAddress();
	
		if (selectedMemoryBlock.getLength() == length) {
			freeList.remove(smallestBlockIndex);
		} else {
			selectedMemoryBlock.setLength(selectedMemoryBlock.getLength() - length);
			selectedMemoryBlock.setBaseAddress(selectedMemoryBlock.getBaseAddress() + length); // Adjust baseAddress
		}
		
		allocatedList.addLast(new MemoryBlock(baseAddress, length));
		return baseAddress;
	}
	
	public void free(int address) {
		for (int i = 0; i < allocatedList.getSize(); i++) {
			MemoryBlock block = allocatedList.getBlock(i);
	
			if (block.getBaseAddress() == address) {
				allocatedList.remove(i);
				int insertIndex = 0;
				for (int j = 0; j < freeList.getSize(); j++) {
					if (block.getBaseAddress() < freeList.getBlock(j).getBaseAddress()) {
						break;
					}
					insertIndex++;
				}
	
				freeList.add(insertIndex, block);
				defrag(); 
				return;
			}
		}
	
		throw new IllegalArgumentException("No allocated block found with base address: " + address);
	}

    /**
     * Performs defragmentation of the free list by merging adjacent blocks.
     */
    public void defrag() {
        if (freeList.getSize() <= 1) return;

		
        Node current = freeList.getFirst();
        while (current != null && current.getNext() != null) {
            MemoryBlock currentBlock = current.getBlock();
            MemoryBlock nextBlock = current.getNext().getBlock();

            if (currentBlock.getBaseAddress() + currentBlock.getLength() == nextBlock.getBaseAddress()) {
                currentBlock.setLength(currentBlock.getLength() + nextBlock.getLength());
                freeList.remove(current.getNext());
            } else {
                current = current.getNext();
            }
			if (currentBlock.getBaseAddress() + currentBlock.getLength() == nextBlock.getBaseAddress()) {
				currentBlock.setLength(currentBlock.getLength() + nextBlock.getLength());
				freeList.remove(current.next);
			}
		}
    }

    /**
     * Returns a textual representation of the current memory space.
     */
    public String toString() {
        return "Free List: " + freeList.toString() + "\nAllocated List: " + allocatedList.toString();
    }
}