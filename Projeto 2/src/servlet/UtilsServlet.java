package servlet;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilsServlet{
    public String createHash(String password){
        byte[] bytePass = null;
        byte[] hashed;

        try{
            bytePass = password.getBytes("UTF-8");
        }catch(UnsupportedEncodingException uee){
            uee.printStackTrace();
        }

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            hashed = md.digest(bytePass);
        }catch(NoSuchAlgorithmException nsae){
            return null;
        }

        return new String(hashed);
    }
}
