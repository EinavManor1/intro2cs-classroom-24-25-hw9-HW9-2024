/**
 * Represents a block of memory.
 * Each memory block has a base address, and a length in words. 
 */
public class MemoryBlock {

    private int baseAddress;
    private int length;

    public MemoryBlock(int baseAddress, int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Block length cannot be negative.");
        }
        this.baseAddress = baseAddress;
        this.length = length;
    }

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

    public boolean canMerge(MemoryBlock other) {
        return other != null && this.baseAddress + this.length == other.baseAddress;
    }

    public void merge(MemoryBlock other) {
        if (other == null || this.baseAddress + this.length != other.baseAddress) {
            throw new IllegalArgumentException("Blocks cannot be merged. Ensure they are adjacent.");
        }
        this.length += other.length;
    }

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
    public String toString() {
        return "(" + baseAddress + " , " + length + ")";
    }
}