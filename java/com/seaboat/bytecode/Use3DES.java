package com.seaboat.bytecode;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * ʹ��3DES �㷨��Ŀ������ִ�мӽ���
 * 
 * @author pangpang
 *3DES=Triple DES ��DES�����㷨��һ��
	 *ʹ��3��56λ����Կ�������μ���
	 */
	public class Use3DES {

		private static final String ALGORITHM = "DESede";//��������㷨
		/**
		 *
		 * @param key	192λ�ļ���Key
		 * @param src	��������
		 * @return
	 */
	public static byte[] encrypt(byte[] key, byte[] src){
		byte[] value = null;
		SecretKey deskey = new SecretKeySpec(key, ALGORITHM);
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, deskey);
			value = cipher.doFinal(src);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
	/**
	 * ����
	 * @param key	192λ�ļ���Key
	 * @param src	����������
	 * @return
	 */
	public static byte[] decrypt(byte[] key, byte[] src){
		byte[] value = null;
		SecretKey deskey = new SecretKeySpec(key, ALGORITHM);
		
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, deskey);
			value = cipher.doFinal(src);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static void main(String[] args) {
		byte[] key = "01234567899876543210abcd".getBytes();
		
		byte[] encoded = encrypt(key, "554278".getBytes());
		System.out.println("���ܺ�"+encoded);
		System.out.println("���ܺ�"+new String(decrypt(key, encoded)));
		
	}
	

}
