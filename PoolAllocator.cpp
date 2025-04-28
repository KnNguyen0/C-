#include <iostream>
#include <vector>

class PoolAllocator {
public:
    PoolAllocator(size_t blockSize, size_t blockCount)
        : blockSize(blockSize), blockCount(blockCount) {
        pool = malloc(blockSize * blockCount);
        freeList.resize(blockCount, true);
    }

    ~PoolAllocator() {
        free(pool);
    }

    void* allocate() {
        for (size_t i = 0; i < blockCount; ++i) {
            if (freeList[i]) {
                freeList[i] = false;
                return static_cast<char*>(pool) + i * blockSize;
            }
        }
        return nullptr; // No free block available
    }

    void freeBlock(void* block) {
        size_t index = (static_cast<char*>(block) - static_cast<char*>(pool)) / blockSize;
        if (index < blockCount) {
            freeList[index] = true;
        }
    }

private:
    size_t blockSize;
    size_t blockCount;
    void* pool;
    std::vector<bool> freeList;
};

int main() {
    PoolAllocator allocator(32, 100);

    void* block1 = allocator.allocate();
    void* block2 = allocator.allocate();

    std::cout << "Allocated blocks" << std::endl;

    allocator.freeBlock(block1);
    allocator.freeBlock(block2);

    std::cout << "Freed blocks" << std::endl;

    return 0;
}
//cl /EHsc PoolAllocator.cpp

//PoolAllocator.exe