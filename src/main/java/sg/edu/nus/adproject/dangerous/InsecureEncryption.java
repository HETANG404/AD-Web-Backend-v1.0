package sg.edu.nus.adproject.dangerous;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class InsecureEncryption {
    public String encrypt(String password) {
        try {
            Cipher cipher = Cipher.getInstance("DES");  // 使用不安全的 DES 加密算法
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec("12345678".getBytes(), "DES"));
            return new String(cipher.doFinal(password.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
