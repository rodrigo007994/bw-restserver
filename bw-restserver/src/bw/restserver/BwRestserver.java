package bw.restserver;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BwRestserver {
public static byte[] readBytes(InputStream inputStream) throws IOException {
    byte[] b = new byte[1024];
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    int c;
    while ((c = inputStream.read(b)) != -1) {
      os.write(b, 0, c);
    }
    return os.toByteArray();
  }

    //startServer("HTTP/1.1 200\nAccept-Ranges: bytes\nContent-Length: %contentLength%\nContent-type: application/pdf\nContent-Disposition: attachment; filename=\"filename.pdf\"\r\n\r\n",8086, contentbytes,5);

    public static void startServer(String headers,int port, byte[] contentbytes, int limit) throws IOException {

        ServerSocket server = new ServerSocket(port);
        Socket socket =server.accept();
        for(int c=0;c<limit||limit==0;socket=server.accept(),c++) {
            try {
                //System.out.println("Listening ...");
                int contentLength = contentbytes.length;
                String httpResponse = headers.replaceFirst("%contentLength%", String.valueOf(contentLength));
                //System.out.println(httpResponse);
                socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
                socket.getOutputStream().write(contentbytes);
            }catch(Exception ex){
                Logger.getLogger(BwRestserver.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        }
                socket.close(); 
    }
}