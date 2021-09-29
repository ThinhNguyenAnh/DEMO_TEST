/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author ASUS
 */
public class PwdProcessor {
    public static String coded (String src)
    {
        String result = "";
        for (int i = 0; i < src.length(); i++) {
            char c = (char)(((int)src.charAt(i)+123)%256);
            result+=c;
        }
        return result;
    }
    
    public static String decode (String src)
    {
        String result = "";
        for (int i = 0; i < src.length(); i++) {
            char c = (char)(((int)src.charAt(i)-123)%256);
            result+=c;
        }
        return result;
    }
}
