class PoolAllocator {
    public:
        PoolAllocator(size_t blockSize, size_t blockCount)
            : blockSize(blockSize), blockCount(blockCount) {
            pool = malloc(blockSize * blockCount);
            freeList = nullptr;
            for (size_t i = 0; i < blockCount; ++i) {
                void* block = static_cast<char*>(pool) + i * blockSize;
                freeBlock(block);
            }
        }
    
        ~PoolAllocator() {
            free(pool);
        }
    
        void* allocate() {
            if (!freeList) return nullptr;
            void* block = freeList;
            freeList = *static_cast<void**>(freeList);
            return block;
        }
    
        void freeBlock(void* block) {
            *static_cast<void**>(block) = freeList;
            freeList = block;
        }
    
    private:
        size_t blockSize;
        size_t blockCount;
        void* pool;
        void* freeList;
    };
    