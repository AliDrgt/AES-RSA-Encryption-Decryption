
# AES_RSA_Encryption_Decryption

## Overview

This project demonstrates the use of AES and RSA encryption techniques to securely encrypt and decrypt files. It includes generating RSA keys, encrypting files with AES, and encrypting the AES key with RSA. The project is written in Java.

## Features

- **AES Encryption and Decryption**
  - Uses AES for symmetric encryption of files.
  - Generates an AES key and Initialization Vector (IV) for encryption.
  - Encrypts and decrypts files using AES.

- **RSA Encryption and Decryption**
  - Uses RSA for asymmetric encryption of the AES key.
  - Generates RSA key pairs.
  - Encrypts the AES key with RSA.
  - Decrypts the AES key with RSA.

## Project Structure

- **aes_enc.java**: Handles AES encryption.
- **aes_dec.java**: Handles AES decryption.
- **rsa_key.java**: Generates RSA key pairs.
- **rsa_enc.java**: Handles RSA encryption of the AES key.
- **rsa_dec.java**: Handles RSA decryption of the AES key.
- **rsaPrivate.key**: RSA private key file.
- **rsaPublic.pub**: RSA public key file.
- **enc.mkv**: Example encrypted file (video).
- **dec.mkv**: Example decrypted file (video).

## Installation

1. **Clone the repository:**
    \`\`\`sh
    git clone https://github.com/yourusername/AES_RSA_Encryption_Decryption.git
    \`\`\`
2. **Navigate to the project directory:**
    \`\`\`sh
    cd AES_RSA_Encryption_Decryption
    \`\`\`
3. **Compile the Java files:**
    \`\`\`sh
    javac aes_enc.java
    javac aes_dec.java
    javac rsa_key.java
    javac rsa_enc.java
    javac rsa_dec.java
    \`\`\`

## Usage

### Generate RSA Keys
\`\`\`sh
java rsa_key
\`\`\`

### AES Encryption
\`\`\`sh
java aes_enc
\`\`\`

### AES Decryption
\`\`\`sh
java aes_dec
\`\`\`

### RSA Encryption of AES Key
\`\`\`sh
java rsa_enc
\`\`\`

### RSA Decryption of AES Key
\`\`\`sh
java rsa_dec
\`\`\`

## Example Workflow

1. **Generate RSA Keys:**
   - This will create \`rsaPrivate.key\` and \`rsaPublic.pub\` files.

2. **Encrypt a File using AES:**
   - The AES key and IV will be generated and saved to files.
   - The file will be encrypted and saved with \`_aes_enc.txt\` suffix.

3. **Encrypt the AES Key using RSA:**
   - The AES key will be encrypted using the RSA private key and saved to \`_rsa.enc\` file.

4. **Decrypt the AES Key using RSA:**
   - The AES key will be decrypted using the RSA public key.

5. **Decrypt the File using AES:**
   - The decrypted AES key and IV will be used to decrypt the file.

## Acknowledgments

This project was created by Ali Durgut.

