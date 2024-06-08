import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Arrays;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

//ALI FATIH DURGUT 20190702068

public class rsa_key{
    public static void main(String[] args) throws Exception {

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        PublicKey pub = kp.getPublic();
        PrivateKey pr = kp.getPrivate();

        try (FileOutputStream out = new FileOutputStream("rsaPrivate" + ".key")) {
            out.write(kp.getPrivate().getEncoded());
        }
        
        try (FileOutputStream out = new FileOutputStream("rsaPublic" + ".pub")) {
            out.write(kp.getPublic().getEncoded());
        }
        
    }
}












