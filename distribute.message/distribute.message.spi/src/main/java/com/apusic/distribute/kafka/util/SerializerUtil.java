package com.apusic.distribute.kafka.util;

import java.io.*;

/**
 * Created by a on 2016/1/19.
 */
public class SerializerUtil {

    public static <T> byte[] serializeToByte(T t) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(t);
            bos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return bos.toByteArray();
    }
    @SuppressWarnings("unchecked")
    public static <T> T deserializeFromByte(byte[] data) {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        T obj = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = (T) ois.readObject();
            bis.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
        return obj;
    }
}
