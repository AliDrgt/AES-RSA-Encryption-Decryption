import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

//ALI FATIH DURGUT 20190702068

public class aes_enc{
    public static void main(String[] args) throws Exception {

        String file_name = "1gb";
        long start_time, end_time;

        start_time = System.currentTimeMillis();
        ////////////////////////////////////////////
        //Generate an Initialization Vector (IV)
        SecureRandom srandom = new SecureRandom();
        byte[] iv = new byte[128/8];
        srandom.nextBytes(iv);
        IvParameterSpec ivspec = new IvParameterSpec(iv);

        String ivFile = file_name + "_iv_file.txt";
        try (FileOutputStream out = new FileOutputStream(ivFile)) {
            out.write(iv);
        }

        //Generating or Loading a Secret Key
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecretKey skey = kgen.generateKey();

        String keyFile = file_name + "_aes_key.txt";
        try (FileOutputStream out = new FileOutputStream(keyFile)) {
            byte[] keyb = skey.getEncoded();
            out.write(keyb);
        }

        //Creating the Cipher
        Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
        ci.init(Cipher.ENCRYPT_MODE, skey, ivspec);

        //Encrypting a String
        String inFile = file_name + ".txt";
        String outFile = file_name + "_aes_enc.txt";
        processFile(ci, inFile, outFile);
        /////////////////////////////////////////////////////
        end_time = System.currentTimeMillis();
        System.out.println("AES ENC Time: " + (end_time - start_time) + " ms");

    }



    static private void processFile(Cipher ci,String inFile,String outFile)
    throws javax.crypto.IllegalBlockSizeException,
           javax.crypto.BadPaddingException,
           java.io.IOException
    {
        try (FileInputStream in = new FileInputStream(inFile);
             FileOutputStream out = new FileOutputStream(outFile)) {
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
}












