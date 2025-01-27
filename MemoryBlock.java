/**
 * Represents a block of memory.
 * Each memory block has a base address and a length in words.
 */
public class MemoryBlock {

<<<<<<< HEAD
    private int baseAddress;
    private int length;

=======
    private int baseAddress; // The address where this memory block begins
    private int length;      // The length of this memory block, in words

    /**
     * Constructs a new memory block with a given base address and length in words.
     *
     * @param baseAddress the address of the first word in this block
     * @param length the length of this memory block, in words
     * @throws IllegalArgumentException if the length is negative
     */
>>>>>>> 383e4c3073a1ac70b410ddcb7e43194c9b8db380
    public MemoryBlock(int baseAddress, int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Block length cannot be negative.");
        }
        this.baseAddress = baseAddress;
        this.length = length;
    }
<<<<<<< HEAD

    public int getBaseAddress() {
        return baseAddress;
    }

    public void setBaseAddress(int baseAddress) {
        this.baseAddress = baseAddress;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Block length cannot be negative.");
        }
        this.length = length;
    }
	
=======

    public int getBaseAddress() {
        return baseAddress;
    }
	public void setBaseAddress(int baseAddress) {
		this.baseAddress = baseAddress;
	}
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Block length cannot be negative.");
        }
        this.length = length;
    }

    /**
     * Checks if this block can merge with another block.
     *
     * @param other the other block to check for adjacency
     * @return true if the blocks are adjacent and can be merged, false otherwise
     */
>>>>>>> 383e4c3073a1ac70b410ddcb7e43194c9b8db380
    public boolean canMerge(MemoryBlock other) {
        return other != null && this.baseAddress + this.length == other.baseAddress;
    }

<<<<<<< HEAD
    public void merge(MemoryBlock other) {
        if (other == null || this.baseAddress + this.length != other.baseAddress) {
=======
    /**
     * Merges this block with another adjacent block.
     *
     * @param other the block to merge with
     * @throws IllegalArgumentException if the blocks cannot be merged
     */
    public void merge(MemoryBlock other) {
        if (!canMerge(other)) {
>>>>>>> 383e4c3073a1ac70b410ddcb7e43194c9b8db380
            throw new IllegalArgumentException("Blocks cannot be merged. Ensure they are adjacent.");
        }
        this.length += other.length;
    }

<<<<<<< HEAD
    public MemoryBlock split(int splitLength) {
        if (splitLength <= 0 || splitLength > this.length) {
            throw new IllegalArgumentException("Invalid split length.");
        }
        MemoryBlock newBlock = new MemoryBlock(this.baseAddress, splitLength);
        this.baseAddress += splitLength;
        this.length -= splitLength;
        return newBlock;
    }

    @Override
=======
    /**
     * Checks if this block is equal to another block in terms of base address and length.
     *
     * @param obj the object to compare with
     * @return true if the blocks are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MemoryBlock other = (MemoryBlock) obj;
        return baseAddress == other.baseAddress && length == other.length;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(baseAddress);
        result = 31 * result + Integer.hashCode(length);
        return result;
    }

    /**
     * Returns a textual representation of this memory block.
     * Example: (208,10)
     *
     * @return a string representation of this memory block
     */
    @Override
>>>>>>> 383e4c3073a1ac70b410ddcb7e43194c9b8db380
    public String toString() {
        return "(" + baseAddress + " , " + length + ")";
    }
}