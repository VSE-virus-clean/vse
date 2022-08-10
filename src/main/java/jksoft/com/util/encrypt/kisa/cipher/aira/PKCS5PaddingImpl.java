package jksoft.com.util.encrypt.kisa.cipher.aira;

/**
 * <pre>
 * PKCS5Padding 구현
 * </pre>
 *
 * @ClassName   : PKCS5PaddingImpl.java
 * @Description : PKCS5Padding 구현
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
public final class PKCS5PaddingImpl implements CryptoPadding{
	
    /*
     * @see kisa.cipher.aira.CryptoPadding#addPadding(byte[], int)
     */
    @Override
    public byte[] addPadding(byte[] source, int blockSize) {
        
        byte[] out = null;
        
        if (source != null){
            int offset = source.length;
            int len = blockSize - (offset % blockSize);
            
            byte paddingOctet = (byte) (len & 0xff);
            out = new byte[offset + len];
            
            System.arraycopy(source, 0, out, 0, source.length);
            
            for (int i = offset; i < out.length; i++) {
                out[i] = paddingOctet;
            }
        }
        
        return out;
    }

    /*
     * @see kisa.cipher.aira.CryptoPadding#removePadding(byte[], int)
     */
    @Override
    public byte[] removePadding(byte[] source, int blockSize) {
        
        byte[] out = null;
        
        if (source != null){
            int len = source.length;
            byte lastByte = source[len - 1];
            int padValue = (int) (lastByte & 0xff);
            
            if (!((padValue < 0x01) || (padValue > blockSize))) {
                int offset = len - padValue;
                
                for (int i = offset; i < len; i++) {
                    if (source[i] != padValue)
                        return null;
                }
                
                out = new byte[offset];
                
                System.arraycopy(source, 0, out, 0, offset);
            }
        }
        
        return out;
    }
}