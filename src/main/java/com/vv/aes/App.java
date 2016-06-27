package com.vv.aes;

import java.io.UnsupportedEncodingException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnsupportedEncodingException
    {
    	byte[] key = "FE7A45426AFF5D14E52897E134F5CC33".getBytes();
    		String content = "{\"CLID\":\"08E4049FF24E4DD8C0E345398ACFB9EE\",\"_s\":\"08E4049FF24E4DD8C0E345398ACFB9EE1398902400\",\"OS\":\"iOS\"}";
    		//String content = "a"//注意填充字符
    		byte[] encode = AesCtr.encode(content.getBytes("utf-8"), key, AesEnum.KEY128);
    		for (byte b : encode) {
    			System.out.print(parseByte2HexStr(b));
    		}
    		System.out.println();
    		
    		byte[] decode = AesCtr.decode(encode, key, AesEnum.KEY128);
    		System.out.println(new String(decode, "UTF-8"));
    	}
    	
    	public static String parseByte2HexStr(byte buf) {
    		StringBuffer sb = new StringBuffer();
    		String hex = Integer.toHexString(buf & 0xFF);
    		if (hex.length() == 1) {
    			hex = '0' + hex;
    		}
    		sb.append(hex.toUpperCase());
    		return sb.toString();
    	}
}
