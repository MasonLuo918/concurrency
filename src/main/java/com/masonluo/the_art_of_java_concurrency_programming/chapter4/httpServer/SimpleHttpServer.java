package com.masonluo.the_art_of_java_concurrency_programming.chapter4.httpServer;

import com.masonluo.the_art_of_java_concurrency_programming.chapter4.ThreadPool.DefaultThreadPool;
import com.masonluo.the_art_of_java_concurrency_programming.chapter4.ThreadPool.ThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
    // 处理HttpRequest的线程池
    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>(1);
    // SimpleHttpServer的根路径
    static String basePath;
    static ServerSocket serverSocket;
    //服务监听端口
    static int port = 8080;

    public static void setPort(int port){
        if(port > 0){
            SimpleHttpServer.port = port;
        }
    }

    public static void setBasePath(String basePath){
        if(basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()){
            SimpleHttpServer.basePath = basePath;
        }
    }

    // 启动SimpleHttpServer
    public static void start() throws Exception{
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while((socket = serverSocket.accept()) != null){
            // 接收一个客户端socket，生成一个HttpRequestHandler，放入线程池执行
//            System.out.println("一个socket");
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }

    static class HttpRequestHandler implements Runnable{

        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;
            try{
                // 读取socket传输过来的字符串
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                // 由相对路径计算出绝对路径
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                // 如果请求资源的后缀为jpg或者ico，则读取资源并输出
                if(filePath.endsWith("jpg") || filePath.endsWith("ico")){
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0;
                    while((i = in.read()) != -1){
                        baos.write(i);
                    }
                    byte[] array = baos.toByteArray();
                    // HTTP 头
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length:" + array.length);
                    out.println("");
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(array, 0, array.length);
                    socket.getOutputStream().write(array, 0, array.length);
                }else{
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type:text/html; charset=UTF-8");
                    out.println("");
                    while((line = br.readLine()) != null){
                        out.println(line);
                    }
                }
                out.flush();
            }catch (Exception e){
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            }finally {
                close(br, in, reader, out, socket);
            }
        }
    }

    // 关闭流或者Socket
    private static void close(Closeable... closeables){
        if(closeables != null){
            for(Closeable closeable:closeables){
                try{
                    closeable.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SimpleHttpServer.setBasePath("/Users/belle/Desktop/concurrency/src/main/java/com/masonluo/the_art_of_java_concurrency_programming/chapter4/httpServer");
        SimpleHttpServer.start();
    }
}
