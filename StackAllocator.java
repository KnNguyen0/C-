public class StackAllocator {
    private int blockSize;
    private int blockCount;
    private byte[] pool;
    private int top;

    public StackAllocator(int blockSize, int blockCount) {
        this.blockSize = blockSize;
        this.blockCount = blockCount;
        this.pool = new byte[blockSize * blockCount];
        this.top = 0;
    }

    public byte[] allocate() {
        if (top + blockSize > pool.length) {
            return null; // No more space available
        }
        byte[] block = new byte[blockSize];
        System.arraycopy(pool, top, block, 0, blockSize);
        top += blockSize;
        return block;
    }

    public void free(byte[] block) {
        if (top - blockSize >= 0) {
            top -= blockSize;
        }
    }

    public static void main(String[] args) {
        StackAllocator allocator = new StackAllocator(32, 100);

        byte[] block1 = allocator.allocate();
        byte[] block2 = allocator.allocate();

        System.out.println("Allocated blocks");

        allocator.free(block1);
        allocator.free(block2);

        System.out.println("Freed blocks");
    }
}
