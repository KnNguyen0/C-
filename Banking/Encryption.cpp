#include "Encryption.h"

std::string encryptDATA(const std::string& data) {
    std::string encrypted = data;
    char key = 'K'; // Simple XOR key
    for (char& c : encrypted) {
        c ^= key;
    }
    return encrypted;
}

std::string decryptDATA(const std::string& encryptedData) {
    // XOR again with the same key to decrypt
    return encryptDATA(encryptedData);
}
