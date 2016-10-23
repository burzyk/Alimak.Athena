package com.jpbnetsoftware.alimak.athena.server;

import com.jpbnetsoftware.alimak.athena.CarManager;
import com.jpbnetsoftware.alimak.athena.ControlServer;
import com.jpbnetsoftware.alimak.athena.Log;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * Created by pburzynski on 23/10/2016.
 */
public class UdpServer implements ControlServer {

    private final CarManager carManager;

    private final Log log;

    private Thread listenThread;

    private boolean isEnabled = true;

    public UdpServer(CarManager carManager, Log log) {
        this.carManager = carManager;
        this.log = log;
    }

    @Override
    public void start() {
        this.log.info("Starting main listener ...");

        this.listenThread = new Thread(() -> {
            try {
                this.log.info("Starting UDP socket");

                byte[] buff = new byte[4 * 3];
                DatagramSocket socket = new DatagramSocket(8080);
                socket.setBroadcast(true);
                socket.setSoTimeout(1000);

                this.log.info("Starting the main loop");

                while (this.isEnabled()) {
                    DatagramPacket packet = new DatagramPacket(buff, buff.length);

                    try {
                        socket.receive(packet);

                        DataInputStream in = new DataInputStream(new ByteArrayInputStream(buff));
                        int motor = in.readInt();
                        float value = in.readFloat();
                        int duration = in.readInt();

                        this.log.info(String.format("received command: %d %f, %d", motor, value, duration));

                        if (motor == 1) {
                            this.carManager.drive(value, duration);
                        }

                        if (motor == 2) {
                            this.carManager.turn(value, duration);
                        }
                    } catch (SocketTimeoutException e) {
                        // ignore normal timeout
                    }
                }
            } catch (Exception e) {
                this.log.error("Unhandled exception in server loop", e);
            }
        });
        this.listenThread.start();

        this.log.info("Listener started");
    }

    @Override
    public void stop() throws InterruptedException {
        this.log.info("Stopping listener ...");
        this.setEnabled(false);
        this.listenThread.join();
        this.carManager.shutdown();
        this.log.info("Listener stopped");
    }

    public synchronized boolean isEnabled() {
        return isEnabled;
    }

    public synchronized void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
