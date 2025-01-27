<<<<<<< HEAD


/**
 * Represents a managed memory space.
 * Manages allocated and free memory blocks with methods to allocate (`malloc`),
 * free, and defragment the space.
 */
public class MemorySpace {
    private LinkedList allocatedList;
    private LinkedList freeList;

    public MemorySpace(int maxSize) {
        allocatedList = new LinkedList();
        freeList = new LinkedList();
        freeList.addLast(new MemoryBlock(0, maxSize)); // Start with one large free block
    }

    public int malloc(int length) {
        if (length <= 0) {
            System.out.println("Invalid allocation request: " + length);
            return -1;
        }

        System.out.println("Starting malloc for length: " + length);
        Node current = freeList.getFirst();
        while (current != null) {
            MemoryBlock block = current.block;
            System.out.println("Inspecting free block: " + block);

            if (block.getLength() >= length) {
                // Allocate memory
                MemoryBlock allocated = new MemoryBlock(block.getBaseAddress(), length);
                allocatedList.addLast(allocated);
                System.out.println("Allocated block: " + allocated);

                if (block.getLength() == length) {
                    freeList.remove(current); // Exact match
                    System.out.println("Exact match. Block removed from free list.");
                } else {
                    // Split the block
                    block.setBaseAddress(block.getBaseAddress() + length);
                    block.setLength(block.getLength() - length);
                    System.out.println("Split block. Remaining free block: " + block);
                }

                System.out.println("Current free list: " + freeList);
                System.out.println("Current allocated list: " + allocatedList);
                return allocated.getBaseAddress();
            }
            current = current.next;
        }

        System.out.println("Allocation failed. No suitable block found.");
        return -1;
    }

    public void free(int address) {
        System.out.println("Freeing memory at address: " + address);
        if (allocatedList.getSize() == 0) {
            System.out.println("No allocated blocks to free.");
            throw new IllegalArgumentException("No allocated blocks to free.");
        }

        Node current = allocatedList.getFirst();
        while (current != null) {
            MemoryBlock block = current.block;
            if (block.getBaseAddress() == address) {
                System.out.println("Found block to free: " + block);
                allocatedList.remove(current);
                freeList.addLast(block);
                System.out.println("Block added to free list.");
                System.out.println("Current free list: " + freeList);
                return;
            }
            current = current.next;
        }

        System.out.println("No block found at address: " + address);
        throw new IllegalArgumentException("No allocated block found with base address: " + address);
    }

    public void defrag() {
        System.out.println("Performing defragmentation...");
        System.out.println("Initial free list: " + freeList);

        if (freeList.getSize() <= 1) {
            System.out.println("No defragmentation needed.");
            return;
        }

        Node current = freeList.getFirst();
        while (current != null && current.next != null) {
            MemoryBlock currentBlock = current.block;
            MemoryBlock nextBlock = current.next.block;

            if (currentBlock.canMerge(nextBlock)) {
                System.out.println("Merging blocks: " + currentBlock + " and " + nextBlock);
                currentBlock.merge(nextBlock);
                freeList.remove(current.next);
            } else {
                current = current.next;
            }
        }

        System.out.println("Free list after defragmentation: " + freeList);
    }

    public MemoryBlock getBlock(int baseAddress) {
        MemoryBlock block = findBlockInList(baseAddress, allocatedList);
        if (block != null) return block;

        block = findBlockInList(baseAddress, freeList);
        if (block != null) return block;

        throw new IllegalArgumentException("No block found with base address: " + baseAddress);
    }

    private MemoryBlock findBlockInList(int baseAddress, LinkedList list) {
        ListIterator iterator = list.iterator();
        while (iterator.hasNext()) {
            MemoryBlock block = iterator.next();
            if (block.getBaseAddress() == baseAddress) {
                return block;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder freeListString = new StringBuilder("Free List: ");
        StringBuilder allocatedListString = new StringBuilder("Allocated List: ");

        ListIterator freeIterator = freeList.iterator();
        while (freeIterator.hasNext()) {
            freeListString.append(freeIterator.next().toString()).append(" ");
        }

        ListIterator allocatedIterator = allocatedList.iterator();
        while (allocatedIterator.hasNext()) {
            allocatedListString.append(allocatedIterator.next().toString()).append(" ");
        }

        return freeListString.toString().trim() + "\n" + allocatedListString.toString().trim();
=======
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
>>>>>>> 383e4c3073a1ac70b410ddcb7e43194c9b8db380
    }
}