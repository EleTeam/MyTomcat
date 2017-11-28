package com.et.mytomcat;

import com.et.mytomcat.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class Response {

    OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        outputStream.write(content.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    public void writeHtmlFile(String path) throws IOException {
        File file = new File("");
        if ("/".equals(path)) {
            path = "ROOT/index.html";
        }
        path = file.getAbsolutePath() + "/webapps/" + path;
        String content;
        try {
            content = FileUtil.getFileContent(path);
        } catch (IOException e) {
            e.printStackTrace();
            content = e.getMessage();
        }
        write(content);
    }
}
