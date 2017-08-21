/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bw.restserver;
import bw.restserver.BwRestserver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigo
 */
public class NewClass {
    public static void main(String[] args){
        byte[] contentbytes=new byte[1];
        BwRestserver brs=new BwRestserver();
        try {
            brs.startServer("HTTP/1.1 200\nAccept-Ranges: bytes\nContent-Length: %contentLength%\nContent-type: application/pdf\nContent-Disposition: attachment; filename=\"filename.pdf\"\r\n\r\n", 8082, contentbytes, 6);
        } catch (IOException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
