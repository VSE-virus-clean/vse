package jksoft.com.util.encrypt.kisa.cipher.aira;

/**
 * <pre>
 * 암호화에서 블럭 사이즈를 맞추기 위해 사용되는 Padding을 추상화 한 Interface
 * </pre>
 *
 * @ClassName   : CryptoPadding.java
 * @Description : 암호화에서 블럭 사이즈를 맞추기 위해 사용되는 Padding을 추상화 한 Interface
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

public interface CryptoPadding {

    /**
     * 요청한 Block Size를 맞추기 위해 Padding을 추가한다.
     * 
     * @param source byte[] 패딩을 추가할 bytes
     * @param blockSize int block size
     * @return byte[] 패딩이 추가 된 결과 bytes
     */
    public byte[] addPadding(byte[] source, int blockSize);

    /**
     * 요청한 Block Size를 맞추기 위해 추가 된 Padding을 제거한다.
     * 
     * @param source byte[] 패딩을 제거할 bytes
     * @param blockSize int block size
     * @return byte[] 패딩이 제거 된 결과 bytes
     */
    public byte[] removePadding(byte[] source, int blockSize);

}
