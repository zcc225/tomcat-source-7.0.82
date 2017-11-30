package com.uikoo9.loader;  
  
import java.io.ByteArrayOutputStream;  
import java.io.FileInputStream;  
import java.io.IOException;  
  
import org.apache.catalina.loader.WebappClassLoader;

import com.seaboat.bytecode.Use3DES;  
  
/** 
 * �Զ����classloader 
 * ���Խ����ļ������� 
 * @author uikoo9 
 */  
public class UClassLoader extends WebappClassLoader{  
      
    /** 
     * Ĭ�Ϲ����� 
     */  
    public UClassLoader() {  
        super();  
    }  
  
    /** 
     * Ĭ�Ϲ����� 
     * @param parent 
     */  
    public UClassLoader(ClassLoader parent) {  
        super(parent);  
    }  
  
    /* (non-Javadoc) 
     * @see org.apache.catalina.loader.WebappClassLoader#findClass(java.lang.String) 
     */  
    public Class<?> findClass(String name) throws ClassNotFoundException {  
        byte[] classBytes = null;  
        try {  
            if(name.contains("uikoo9")){  
                System.out.println("++++++" + name);  
                classBytes = loadClassBytesEncrypt(name);  
            }else{  
                System.out.println("-------" + name);  
                classBytes = loadClassBytesDefault(name);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);  
        if (cl == null)  
            throw new ClassNotFoundException(name);  
        return cl;  
    }  
      
    @Override  
    public Class<?> loadClass(String name) throws ClassNotFoundException {  
        if(name.contains("uikoo9")){  
            return findClass(name);  
        }else{  
            return super.loadClass(name);  
        }  
    }  
  
    /** 
     * ���ؼ��ܺ��class�ֽ��� 
     * @param name 
     * @return 
     * @throws IOException 
     */  
    private byte[] loadClassBytesEncrypt(String name) throws IOException {  
        String cname = name.replace('.', '/') + ".uikoo9";  
        FileInputStream in = null;  
        in = new FileInputStream(cname);  
        try {  
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();  
            int ch;  
            while ((ch = in.read()) != -1) {  
                buffer.write((byte)(ch - 2));  
            }  
            in.close();  
//            return buffer.toByteArray();  
           return  Use3DES.decrypt("01234567899876543210abcd".getBytes(),buffer.toByteArray());
        } finally {  
            in.close();  
        }  
    }  
      
    /** 
     * ������ͨ��class�ֽ��� 
     * @param name 
     * @return 
     * @throws IOException 
     */  
    private byte[] loadClassBytesDefault(String name) throws IOException {  
        String cname = name.replace('.', '/') + ".class";  
        FileInputStream in = null;  
        cname.lastIndexOf(".");
        System.getProperty("user.dir");
        in = new FileInputStream(cname);  
        try {  
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();  
            int ch;  
            while ((ch = in.read()) != -1) {  
                buffer.write((byte)ch);  
            }  
            in.close();  
            return buffer.toByteArray();  
        } finally {  
            in.close();  
        }  
    }  
}  