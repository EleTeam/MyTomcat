package com.et.mytomcat;


import com.et.mytomcat.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Bootstrap {

    private static ServerSocket serverSocket;
    private static Socket socket;
    private static long count = 0;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(9999); //启动服务端的socket，此时用户可以连接
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (true) {
            try {
                socket = serverSocket.accept(); //阻塞，等待用户连接后才往下运行

                //记录每次连接数
                count++;
                System.out.println("第" + count + "次连接");

                /* 从输入流中读取到Request对象 */
                InputStream inputStream = socket.getInputStream();
                Request request = new Request(inputStream);

                /* 服务器返回信息到输出流 */
                OutputStream outputStream = socket.getOutputStream();
                Response response = new Response(outputStream);

                /* 业务逻辑 */
                String uriStr = request.getUriStr();
                if (uriStr.endsWith(".html") || uriStr.equals("/")) {
                    response.writeHtmlFile(uriStr);
                } else {
                    response.write("TODO");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}