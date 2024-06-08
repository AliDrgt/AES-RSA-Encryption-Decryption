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

public class rsa_enc{
    public static void main(String[] args) throws Exception {

        String file_name = "1gb";
        long start_time, end_time;
        SecureRandom srandom = new SecureRandom();

        start_time = System.currentTimeMillis();
        ////////////////////////////////
        //Loading the RSA Private Key
        String pvtKeyFile = "rsaPrivate.key";
        byte[] bytes = Files.readAllBytes(Paths.get(pvtKeyFile));
        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey pvt = kf.generatePrivate(ks);

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        SecretKey skey = kgen.generateKey();

        byte[] iv = new byte[128/8];
        srandom.nextBytes(iv);
        IvParameterSpec ivspec = new IvParameterSpec(iv);

        FileOutputStream out = new FileOutputStream(file_name + "_rsa.enc");
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pvt);
        byte[] b = cipher.doFinal(skey.getEncoded());
        out.write(b);
        out.write(iv);


        Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
        ci.init(Cipher.ENCRYPT_MODE, skey, ivspec);
        try (FileInputStream in = new FileInputStream(file_name + ".txt")) {
            processFile(ci, in, out);
        }

        ////////////////////////////////
        end_time = System.currentTimeMillis();
        System.out.println("RSA ENC Time: " + (end_time - start_time) + " ms");
        out.close();

    }

    static private void processFile(Cipher ci,InputStream in,OutputStream out)
    throws javax.crypto.IllegalBlockSizeException,
           javax.crypto.BadPaddingException,
           java.io.IOException
        {
                byte[] ibuf = new byte[1024];
                int len;
                while ((len = in.read(ibuf)) != -1) {
                    byte[] obuf = ci.update(ibuf, 0, len);
                    if ( obuf != null ) out.write(obuf);
                }
                byte[] obuf = ci.doFinal();
                if ( obuf != null ) out.write(obuf);
        }
}












