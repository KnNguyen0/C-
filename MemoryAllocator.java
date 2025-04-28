public class MemoryAllocator {
    private int blockSize;
    private int blockCount;
    private byte[] pool;
    private boolean[] freeList;

    public MemoryAllocator(int blockSize, int blockCount) {
        this.blockSize = blockSize;
        this.blockCount = blockCount;
        this.pool = new byte[blockSize * blockCount];
        this.freeList = new boolean[blockCount];
        for (int i = 0; i < blockCount; i++) {
            freeList[i] = true;
        }
    }

    public byte[] allocate() {
        for (int i = 0; i < blockCount; i++) {
            if (freeList[i]) {
                freeList[i] = false;
                return getBlock(i);
            }
        }
        return null; // No free block available
    }

    public void free(byte[] block) {
        int index = getBlockIndex(block);
        if (index != -1) {
            freeList[index] = true;
        }
    }

    private byte[] getBlock(int index) {
        int start = index * blockSize;
        byte[] block = new byte[blockSize];
        System.arraycopy(pool, start, block, 0, blockSize);
        return block;
    }

    private int getBlockIndex(byte[] block) {
        for (int i = 0; i < blockCount; i++) {
            int start = i * blockSize;
            boolean match = true;
            for (int j = 0; j < blockSize; j++) {
                if (pool[start + j] != block[j]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MemoryAllocator allocator = new MemoryAllocator(32, 100);

        byte[] block1 = allocator.allocate();
        byte[] block2 = allocator.allocate();

        System.out.println("Allocated blocks");

        allocator.free(block1);
        allocator.free(block2);

        System.out.println("Freed blocks");
    }
}
