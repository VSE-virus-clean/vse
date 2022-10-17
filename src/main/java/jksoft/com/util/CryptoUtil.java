package jksoft.com.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// import org.apache.commons.codec.binary.Base64;

import jksoft.com.util.encrypt.kisa.cipher.aira.ARIACipher;

/**
 * <pre>
 * 비밀번호및 개인정보 암호화/복호화 
 * 
 * - 개인정보는 ARIA 알고리즘을 사용한다.(AES와 동일규격)
 * - 참고 사이트
 *  http://seed.kisa.or.kr/iwt/ko/sup/EgovAriaInfo.do
 *  http://www.egovframe.org/wiki/doku.php?id=egovframework:rte2:fdl:encryption_decryption
 * </pre>
 *
 * @ClassName   : CryptoUtil.java
 * @Description : 암호화 관련 Class
 * @author Jeong.hyoungjea
 * @since 2014. 2. 5.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2014. 2. 5.     Jeong.hyoungjea     최초 생성
 *  2017.12.23.     Jeong.hyoungjea     sun.misc 라이브러리를 apache.commons.codec 라이브러리로 변경함.
 * </pre>
 */
public class CryptoUtil {

	/**
     * ARIA 암호화 비밀번호
     */
    public final static String ARIA_PASSWORD = "JKSoft-ariacare-service";
    
    /**
     * ARIA Password 암호화 알고리즘
     */
    public final static String ARIA_PASSWORD_ALGORITHM = "MD5"; 
    
    
    /**
     * 문자열을 ARIR알고리즘으로 암호화 한다.
     * - 복호화 가능
     *
     * @param str 암호화할 문자열
     * @return
     */
    public static String encryptARIA(String str){
        
        String strRtn = "";
        
        try {
            
            if(!(str == null || "".equals(str))){
                ARIACipher ariaCipher = new ARIACipher();
                
                ariaCipher.setPassword(encrypt(ARIA_PASSWORD, ARIA_PASSWORD_ALGORITHM));
            
                strRtn = getBASE64Encoder(ariaCipher.encrypt(str.getBytes("UTF-8")));
            }
            
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(); 
        }
        
        return strRtn;
    }
    
    /**
     * ARIR알고리즘으로 암호화된 문자열을 복호화 한다.
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String decryptARIA(String str){
        
        String strRtn = "";
        
        try {
            
            if(!(str == null || "".equals(str))){
                ARIACipher ariaCipher = new ARIACipher();
                
                ariaCipher.setPassword(encrypt(ARIA_PASSWORD, ARIA_PASSWORD_ALGORITHM));
                
                strRtn = new String(ariaCipher.decrypt(getBASE64Decoder(str)), "UTF-8");
            }
            
        } catch (Exception e) {
            strRtn = "암호화다시";
            //throw new IllegalArgumentException(); 
        }
        
        return strRtn;
    }
    
    /**
     * 단반향  암호화 16진수로 변환하여 리턴한다.
     * - 복호화 불가능 
     * 
     * @param str   암호화할 문자열 
     * @param strAlgorithm  알고리즘명(SHA-256, SHA-512, MD5)
     * @return
     */
    public static String encrypt(String str, String strAlgorithm){

        String strSHA = "";
        
        try{
            if(!(str == null || "".equals(str))){
                MessageDigest messageDigest = MessageDigest.getInstance(strAlgorithm); 
                strSHA = getHexa(messageDigest.digest(str.getBytes()));
            }
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace(); 
            strSHA = "";
        }
         
        return strSHA;
    }
    
    /**
     * HASH 값을 16진수로 변환
     * @param btHash HASH CODE
     * @return
     */
    public static String getHexa(byte[] _btHash){       
        StringBuffer sb = new StringBuffer(_btHash.length * 2); 
        
        for(int i=0; i < _btHash.length; i++){                       
            sb.append(String.format("%02x", 0xff&(char)_btHash[i]));
        }
        
        return sb.toString();
    }
    
    /**
     * Hash 값을 BASE64Encoder로 변환
     *
     * @param btHash
     * @return
     */
    public static String getBASE64Encoder(byte[] btHash)
    {
        /*
    	Base64 baseEncoder = new Base64();
        return baseEncoder.encodeToString(btHash).trim();
        */
    	
    	Encoder encoder = Base64.getEncoder();
    	byte[] encodedBytes = encoder.encode(btHash);
    	return (new String(encodedBytes));
    }
    
    /**
     *  Hash 값을 BASE64Encoder로 변환
     *
     * @param strHash
     * @return
     */
    public static String getBASE64Encoder(String strHash)
    {
    	/*
        Base64 baseEncoder = new Base64();
        return baseEncoder.encodeToString(strHash.getBytes());
        */
    	
    	Encoder encoder = Base64.getEncoder();
    	byte[] encodedBytes = encoder.encode(strHash.getBytes());
    	return (new String(encodedBytes));
    }
   
   /**
    * Hash 값을 BASE64Encoder로 Decoder
    * 
    * @param _strHash
    * @return byte
    * @throws Exception
    */
    public static byte[] getBASE64Decoder(String _strHash) throws Exception
    {
    	/*
        Base64 baseDecoder = new Base64();
        return baseDecoder.decode(_strHash);
        */
    	
    	Decoder decoder = Base64.getDecoder();
    	return decoder.decode(_strHash);
    }  
    
    /**
     * Hash 값을 BASE64Encoder로 Decoder
     * 
     * @param _strHash
     * @return string
     * @throws Exception
     */
    public static String getBASE64Decoder2(String _strHash) throws Exception
    {
    	/*
        Base64 baseDecoder = new Base64();
        return new String(baseDecoder.decode(_strHash));
        */
    	
    	Decoder decoder = Base64.getDecoder();
    	byte[] decodedBytes = decoder.decode(_strHash);
    	return (new String(decodedBytes, "UTF-8"));
    } 
    
    /**
     * SHA-512 암호화
     *
     * @param str   암호화할 문자열
     * @return
     */
    public static String encryptSHA512(String str){
        return encrypt(str, "SHA-512");
    }
    
    /**
     * MD5 암호화
     *
     * @param str   암호화할 문자열
     * @return
     */
    public static String encryptMD5(String str){
        return encrypt(str, "MD5");
    }
    
    /**
     * BASE64복호화후에 사용자 비밀 번호 암호화
     * - MD5 + SHA-512
     *
     * @param strPasswordEnc
     * @return
     * @throws Exception
     */
    public static String encodeUserPassword64(String strPasswordEnc) throws Exception{
        
        String strRtn = "";
        
        if(strPasswordEnc.isEmpty()){
            throw new IllegalArgumentException("exception.binding");
        }else{
        	
        	System.out.println("Hash ---------------------------------- " + strPasswordEnc);
            strRtn = CryptoUtil.encodeUserPassword(new String(CryptoUtil.getBASE64Decoder(strPasswordEnc)));
        }
        
        return strRtn;
    }
    
    /**
     * 사용자 비밀 번호 암호화
     * - MD5 + SHA-512
     *
     * @param strPasswordEnc
     * @return
     * @throws Exception
     */
    public static String encodeUserPassword(String strPassword) throws Exception{
        
        String strRtn = "";
        
        if(strPassword.isEmpty()){
            throw new IllegalArgumentException("exception.binding");
        }else{
            strRtn = CryptoUtil.encryptSHA512(CryptoUtil.encryptMD5(strPassword));
        }
        
        return strRtn;
    }
    
    /**
     * AES128 CBC 암호화
     * @param plainText
     * @param key
     * @param ivVector
     * @return
     * @throws Exception
     */
    public static String encryptAES_CBC(String plainText, String key, String ivVector) throws Exception {
    	
    	IvParameterSpec iv = new IvParameterSpec(ivVector.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
         
    	Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] encrypted = c.doFinal(plainText.getBytes("UTF-8"));
        
        return getBASE64Encoder(encrypted);
    }

    /**
     * AES128 CBC 복호화
     * @param plainText
     * @param key
     * @param ivVector
     * @return
     * @throws Exception
     */
    public static String decryptAES_CBC(String plainText, String key, String ivVector) throws Exception {
    	
    	IvParameterSpec iv = new IvParameterSpec(ivVector.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        
    	Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, secretKey, iv);
        
        // byte[] byteStr = Base64.decodeBase64(plainText.getBytes("UTF-8"));
        Decoder decoder = Base64.getDecoder();
        byte[] byteStr = decoder.decode(plainText.getBytes("UTF-8"));
        
        return new String(c.doFinal(byteStr), "UTF-8");
    }
}
