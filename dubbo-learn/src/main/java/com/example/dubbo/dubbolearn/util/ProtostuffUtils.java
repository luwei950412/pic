package com.example.dubbo.dubbolearn.util;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

//import static io.protostuff.runtime.RuntimeSchema.getSchema;

public class ProtostuffUtils {
    private static LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

    private static Map<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap<>();

//    序列化方法，把指定对象序列化成字节数组
    public static <T> byte[] serialize(T obj){
        Class<T> clazz = (Class<T>)obj.getClass();
        Schema<T> schema = getSchema(clazz);
        byte[] data;
        try {
            data = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        }finally {
            buffer.clear();
        }
        return  data;
    }

    public static <T> T deserialize(byte[] data, Class<T> clazz){
        Schema<T> schema = getSchema(clazz);
        T obj = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(data, obj, schema);
        return obj;
    }

    private static <T> Schema<T> getSchema(Class<T> clazz){
        Schema<T> schema = (Schema<T>) schemaCache.get(clazz);
        if(Objects.isNull(schema)){
            schema = RuntimeSchema.getSchema(clazz);
            if(Objects.nonNull(schema)){
                schemaCache.put(clazz, schema);
            }
        }
        return schema;
    }
}
