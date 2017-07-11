package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zzy on 2017/6/5.
 */
public class MD5Generator {
    public static String stringMD5(String input){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] inputByteArray = input.getBytes();
            messageDigest.update(inputByteArray);
            byte[] outputByteArray = messageDigest.digest();
            return byteArrayToString(outputByteArray);
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    public static String byteArrayToString(byte[] outputByteArray){
        char[] hex = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
        char[] result = new char[outputByteArray.length*2];
        int index = 0;
        for(byte b : outputByteArray){
            result[index++] = hex[b>>>4 & 0xf];
            result[index++] = hex[b & 0xf];
        }
        return new String(result);

    }

}
