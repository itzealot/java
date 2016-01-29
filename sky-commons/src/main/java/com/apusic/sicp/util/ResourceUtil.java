package com.apusic.sicp.util;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResourceUtil {
    private static final Log log = LogFactory.getLog(ResourceUtil.class);

    public static void close(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable item : closeables) {
            try {
                if(item != null){
                    item.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public static void close(Socket socket) {
        if(socket == null){
            return;
        }
        try {
            socket.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
