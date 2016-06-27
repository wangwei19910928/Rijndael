package com.vv.aes;

public class AesCtr {
	private static final int ENCODE = -1;
	private static final int DECODE = -2;
		
	private static byte[] cipher(byte[] content, byte[] password, AesEnum pwdMode, int enMode) {
		int pwdLen = pwdMode.getValue()/8;
		byte[] pwd = new byte[pwdLen];
		byte[] pwdBytes = password;
		int copyLen = pwdLen>pwdBytes.length?pwdBytes.length:pwdLen;
		System.arraycopy(pwdBytes, 0, pwd, 0, copyLen);
		
		byte[] cntBytes = content;
		int cntLen = (cntBytes.length + 15)/16*16;
		byte[] cnt = new byte[cntLen];
		System.arraycopy(cntBytes, 0, cnt, 0, cntBytes.length);
		
		int len = cntLen;
		byte[] code = new byte[len];
		byte[][] w = Aes.keyExpansion(pwd);
		for(int i=0; i<len/16; i++) {
			byte[] state = new byte[16];
			System.arraycopy(cnt, i*16, state, 0, 16);
			if(ENCODE == enMode) {
				state = Aes.encipher(state, w);
			} if(DECODE == enMode) {
				state = Aes.decipher(state, w);
			}
			System.arraycopy(state, 0, code, i*16, 16);
		}
		return code;
	}
	
	public static byte[] encode(byte[] content, byte[] password, AesEnum pwdMode) {
		return cipher(content, password, pwdMode, ENCODE);
	}
	
	public static byte[] decode(byte[] content, byte[] password, AesEnum pwdMode) {
		return cipher(content, password, pwdMode, DECODE);
	}
}

