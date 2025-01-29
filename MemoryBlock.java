/**
 * Represents a block of memory.
 * Each memory block has a base address, and a length in words. 
 */
public class MemoryBlock {

	int baseAddress;  // the address where this memory block begins
	int length;       // the length of this memory block, in words

	/**
	 * Constructs a new memory block with a given base address and length in words 
	 * 
	 * @param baseAddress
	 *        the address of the first word in this block
	 * @param length
	 *        the length of this memory block, in words
	 */
	public MemoryBlock(int baseAddress, int length) {
		this.baseAddress = baseAddress;
		this.length = length;
	}

	/**
	 * Checks if this block has the same base address and length as the given block
	 * 
	 * @param other
	 *        the given block
	 * @return true if this block equals the other block, false otherwise
	 */
	public boolean equals(MemoryBlock other) {
		return baseAddress == other.baseAddress && length == other.length;
	}

	/**
	 * A textual representation of this memory block, for debugging.
	 * The block's contents appears within parentheses.
	 * For example: (208,10)
	 */
	public String toString() {
		return "(" + baseAddress + " , " + length +")";
	}
}