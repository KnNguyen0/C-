#ifndef ENCRYPTION_H
#define ENCRYPTION_H

#include <string>

std::string  encryptDATA(const std::string& data);
std::string decryptDATA(const std::string& encryptedData);

#endif // ENCRYPTION_H