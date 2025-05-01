#include "UserManagement.h"

#include <fstream>

unsigned long simpleHash(const std::string& str) {
    unsigned long hash = 5381;
    for (char c : str) {
        hash = ((hash << 5) + hash) + c; // hash * 33 + c
    }
    return hash;
}

void registerUser(const std::string& username, const std::string& password) {
    std::ofstream file("users.txt", std::ios::app);
    file << username << " " << simpleHash(password) << std::endl;
    file.close();
}

bool loginUser(const std::string& username, const std::string& password) {
    std::ifstream file("users.txt");
    std::string storedUsername;
    unsigned long storedHash;
    while (file >> storedUsername >> storedHash) {
        if (storedUsername == username && storedHash == simpleHash(password)) {
            return true;
        }
    }
    return false;
}
