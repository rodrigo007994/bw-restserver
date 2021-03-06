package bw.restserver;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URLDecoder;

public class BwRestserver {

    //startServer("HTTP/1.1 200\nAccept-Ranges: bytes\nContent-Length: %contentLength%\nContent-type: application/pdf\nContent-Disposition: attachment; filename=\"filename.pdf\"\r\n\r\n",8086, contentbytes,5);

    public static void startServer(String headers,int port, byte[] contentbytes, int limit) throws IOException {
        ServerSocket server = new ServerSocket(port);
        Socket socket =server.accept();
        for(int c=0;c<limit||limit==0;socket=server.accept(),c++) {
            try {
                Logger.getLogger(BwRestserver.class.getName()).log(Level.INFO, "Listening port: {0}", port);
                int contentLength = contentbytes.length;
                String httpResponse = headers.replaceFirst("%contentLength%", String.valueOf(contentLength));
                Logger.getLogger(BwRestserver.class.getName()).log(Level.INFO, "Header: {0}", httpResponse);
                socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
                socket.getOutputStream().write(contentbytes);
            }catch(IOException ex){
                Logger.getLogger(BwRestserver.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        }
                socket.close();
                Logger.getLogger(BwRestserver.class.getName()).log(Level.INFO, "Socket Closed.");
    }
}