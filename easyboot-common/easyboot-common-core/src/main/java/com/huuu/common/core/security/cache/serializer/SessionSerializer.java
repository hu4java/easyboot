package com.huuu.common.core.security.cache.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.*;

/**
 * redis 序列化
 * @author chenzhenhu
 */
public class SessionSerializer implements RedisSerializer<Object> {

    private static final int STREAM_SIZE = 128;

    @Override
    public byte[] serialize(Object object) throws SerializationException {

        byte[] bytes = new byte[0];
        if (null == object) {
            return bytes;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(STREAM_SIZE);

        if (!(object instanceof Serializable)) {
            throw new SerializationException("object must to be implement Serializable");
        }
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(object);
            outputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bytes;
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {

        if (null == bytes || bytes.length == 0) {
            return null;
        }

        Object object;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);
            object = inputStream.readObject();
        } catch (Exception e) {
            throw new SerializationException(e.getMessage(), e);
        }

        return object;
    }

//    @Slf4j
//    public static class MyClassLoaderObjectInputStream extends ObjectInputStream {
//
//        public MyClassLoaderObjectInputStream(ByteArrayInputStream inputStream) throws IOException {
//            super(inputStream);
//        }
//
//        @Override
//        protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
//            String name = desc.getName();
//            try {
//                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//                return Class.forName(name, false, classLoader);
//            } catch (Throwable ex) {
//            }
//            return super.resolveClass(desc);
//        }
//    }
}
