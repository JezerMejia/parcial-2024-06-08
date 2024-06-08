package ni.factorizacion.parcial20240608.utils;

import org.apache.tomcat.util.codec.binary.Base64;

public class Encrypt {
    public static String encryptPassword(String password) {
        return Base64.encodeBase64String(password.getBytes());
    }

    public static String decryptPassword(String password) {
        return new String(Base64.decodeBase64(password));
    }

}
