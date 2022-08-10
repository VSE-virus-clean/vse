package jksoft.com.util.encrypt.kisa.cipher.aira;

import java.security.InvalidKeyException;

/**
 * <pre>
 * ARIA 암호화 Class
 * </pre>
 *
 * @ClassName   : ARIACipher.java
 * @Description : ARIA 암호화 Class
 *                - 전자정부에 사용하는 ARIACipher.java를 수정함.
 * @author Jeong.hyoungjea
 * @since 2014. 2. 24.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2014. 2. 24.     Jeong.hyoungjea     최초 생성
 * </pre>
 */
public class ARIACipher {
    
    /**
     * 블록크기/ 키사이즈(16:128, 192:24, 32:256)
     */
    private static final int BLOCK_SIZE = 32;
    
    /**
     * 마스터
     */
    public String masterKey = null;

    /**
     * 암호 설정.
     * 
     * @param masterKey 암호문자열
     */
    public void setPassword(String masterKey) {
        this.masterKey = (masterKey.length() > BLOCK_SIZE ? masterKey.substring(0, BLOCK_SIZE) : masterKey);
    }

    /**
     * 바이트 배열 리턴 암호화
     * 
     * @param data 암호화할 바이트배열
     * @return 암호화된 바이트배열
     */
    public byte[] encrypt(byte[] data) {
        try {
            CryptoPadding padding = new AnsiX923Padding();
            
            byte[] mk = padding.addPadding(masterKey.getBytes(), BLOCK_SIZE);
            
            ARIAEngine instance = new ARIAEngine(256);
            
            return instance.encrypt(data, mk);
        } catch (InvalidKeyException ike) {
            throw new RuntimeException(ike);
        }
    }

    /**
     * 복호화 배열 리턴 암호화.
     * 
     * @param encryptedData 복호화할 데이타 바이트배열
     * @return 복호화된 바이트배열
     */
    public byte[] decrypt(byte[] encryptedData) {
        try {
            CryptoPadding padding = new AnsiX923Padding();
            
            byte[] mk = padding.addPadding(masterKey.getBytes(), BLOCK_SIZE);
            
            ARIAEngine instance = new ARIAEngine(256);
            
            return instance.decrypt(encryptedData, mk);
        } catch (InvalidKeyException ike) {
            throw new RuntimeException(ike);
        }
    }
}