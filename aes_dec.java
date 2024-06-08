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

public class aes_dec{
    public static void main(String[] args) throws Exception {

        String file_name = "1gb";

        //Generate an Initialization Vector (IV)
        SecureRandom srandom = new SecureRandom();
        long start_time, end_time;
        start_time = System.currentTimeMillis();
        ///////////////////////////////////////////////
        String ivFile = file_name + "_iv_file.txt";
        byte[] iv = Files.readAllBytes(Paths.get(ivFile));
        IvParameterSpec ivspec = new IvParameterSpec(iv);

        //Generating or Loading a Secret Key
        String keyFile = file_name + "_aes_key.txt";
        byte[] keyb = Files.readAllBytes(Paths.get(keyFile));
        SecretKeySpec skey = new SecretKeySpec(keyb, "AES");


        //Creating the Cipher
        Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
        ci.init(Cipher.DECRYPT_MODE, skey, ivspec);

        //Encrypting a String
        String inFile = file_name + "_aes_enc.txt";
        String outFile = file_name + "_aes_dec.txt";
        processFile(ci, inFile, outFile);
        ///////////////////////////////////////////////
        end_time = System.currentTimeMillis();
        System.out.println("AES DEC Time: " + (end_time - start_time) + " ms");

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












