#ifndef USERMANAGEMENT_H
#define USERMANAGEMENT_H

#include <string>

unsigned long simpleHash(const std::string& str);
void registerUser(const std::string& username, const std::string& password);
bool loginUser(const std::string& username, const std::string& password);

#endif // USERMANAGEMENT_H
