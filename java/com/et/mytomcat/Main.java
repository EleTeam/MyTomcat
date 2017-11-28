package com.et.mytomcat;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static ServerSocket serverSocket;
    private static Socket socket;
    private static long count = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        serverSocket = new ServerSocket(9999); //启动服务端的socket，此时用户可以连接

        while (true) {
            socket = serverSocket.accept(); //阻塞，等待用户连接后才往下运行

            //记录每次连接数
            count++;
            System.out.println("第" + count + "次连接");

            /* 从输入流中读取字节到字节数组 */
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[10240];
            int len = inputStream.read(bytes);

            //字节数组转为字符串
            String reqStr = new String(bytes, 0, len);
            System.out.println(reqStr);

            /* 服务器返回信息到输出流 */
            OutputStream outputStream = socket.getOutputStream();
            String respStr = "<html><title>服务器返回信息到输出流</title><body>" + reqStr + "</body></html>";
            outputStream.write(respStr.getBytes());
        }
    }

}