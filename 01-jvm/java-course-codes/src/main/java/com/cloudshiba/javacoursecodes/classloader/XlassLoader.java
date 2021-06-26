package com.cloudshiba.javacoursecodes.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * 第一周作业:
 * 2.（必做）自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，
 * 此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。 文件群里提供。
 */
public class XlassLoader extends ClassLoader {
    private static final Logger logger = LoggerFactory.getLogger(XlassLoader.class);

    public static void main(String[] args) throws
            ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        final String className = "Hello";
        final String methodName = "hello";
        ClassLoader classLoader = new XlassLoader();
        Class<?> clazz = classLoader.loadClass(className);
        // List methods
        for (Method m : clazz.getDeclaredMethods()) {
            logger.info("{}.{}()", clazz.getSimpleName(), m.getName());
        }
        // Create instance
        Object instance = clazz.getDeclaredConstructor().newInstance();
        // Invoke method
        Method method = clazz.getMethod(methodName);
        method.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException
    {
        // Replace package separator with file separator
        String resourcePath = name.replace(".", File.separator);
        // File name suffix
        final String suffix = ".xlass";
        // Get input stream
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourcePath + suffix);

        try {
            // Returns an estimate of the number of bytes that can be read
            int length = inputStream.available();
            byte[] bytes = new byte[length];
            inputStream.read(bytes);
            byte[] classBytes = decode(bytes);
            // Converts an array of bytes into an instance of class Class.
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            logger.error("Exception by {}", e.getMessage());
            throw new ClassNotFoundException(name, e);
        } finally {
            close(inputStream);
        }
    }

    // Decode bytes
    private static byte[] decode(byte[] bytes) {
        byte[] targetBytes = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            targetBytes[i] = (byte) (255 - bytes[i]);
        }
        return targetBytes;
    }

    // Close source
    private static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                logger.error("Exception by {}", e.getMessage());
            }
        }
    }
}
