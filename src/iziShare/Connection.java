/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iziShare;

import Data.EziPacketProcessor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elwin
 */
public final class Connection implements Runnable {

    private ServerSocket serverSocket;
    private EziPacketProcessor processor;
    private ArrayList<EziPeer> peers;
    private Thread thread;
    private boolean run = true;
    

    protected Connection(EziPacketProcessor processor) {
        this.peers = new ArrayList<>();
        this.processor = processor;
        try {
            serverSocket = new ServerSocket(4545);
        } catch (IOException ex) {
            Logger.getLogger(EziUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void stop() {
        run = false;
        thread = null;
    }

    protected void start() {
        run = true;
        thread = new Thread(this);
        thread.start();
    }

    protected void acceptSocket() {
        try {
            Socket socket;
            socket = serverSocket.accept();
            EziPeer peer = new EziPeer(socket, processor);
            peers.add(peer);
        } catch (IOException ex) {
            Logger.getLogger(EziUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<EziPeer> getNodes() {
        return this.peers;
    }

    @Override
    public void run() {
        System.out.println("Connection: serversocket listening");
        while (run) {
            acceptSocket();
        }
        System.out.println("Connection: serversocket stopped listening");
    }
}
