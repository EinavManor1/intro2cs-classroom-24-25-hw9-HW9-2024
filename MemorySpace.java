

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
    }
}