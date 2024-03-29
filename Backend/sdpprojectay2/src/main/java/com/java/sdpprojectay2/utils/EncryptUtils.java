package com.java.sdpprojectay2.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;



public class EncryptUtils implements PasswordEncoder {

    private final String secretKey = "ArisDMXEIA";

    private final byte[] salt = {
            (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
            (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
    };
    private final int iterationCount = 19;

    public EncryptUtils() {

    }

    public String encrypt(String plainText) {
        try {
            KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
            SecretKey key;
            key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            String charSet = "UTF-8";
            byte[] in = plainText.getBytes(charSet);
            byte[] out = ecipher.doFinal(in);
            String encStr;
            encStr = new String(Base64.getEncoder().encode(out));
            return encStr;
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException | BadPaddingException | UnsupportedEncodingException | IllegalBlockSizeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(String encryptedText) {
        try {
            KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
            SecretKey key;
            key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            Cipher dcipher = Cipher.getInstance(key.getAlgorithm());
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
            byte[] enc = Base64.getDecoder().decode(encryptedText);
            byte[] utf8 = dcipher.doFinal(enc);
            String charSet = "UTF-8";
            return new String(utf8, charSet);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException | BadPaddingException | UnsupportedEncodingException | IllegalBlockSizeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        EncryptUtils cryptoUtil = new EncryptUtils();
        String plain = "12345678";
        String enc = cryptoUtil.encrypt(plain);
        System.out.println("Original text: " + plain);
        System.out.println("Encrypted text: " + enc);

        String plainAfter = cryptoUtil.decrypt(plain);
        System.out.println("Original text after decryption: " + plainAfter);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        String plainText = rawPassword.toString();
        return encrypt(plainText);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }
}
