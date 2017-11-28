package com.et.mytomcat;

import java.io.IOException;
import java.io.InputStream;

public class Request {

    String uriStr;

    public Request(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[10240];
        int len = inputStream.read(bytes);
        if (len > 0) {
            String head = new String(bytes, 0, len);
            uriStr = head.substring(head.indexOf("/"), head.indexOf("HTTP/1.1")-1);
        }
    }

    public String getUriStr() {
        return uriStr;
    }

    public void setUriStr(String uriStr) {
        this.uriStr = uriStr;
    }
}
