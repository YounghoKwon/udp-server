package com.example.open_master_temp.controller;

import com.example.open_master_temp.entity.ListGroup;
import com.example.open_master_temp.entity.TransferObject;
import com.example.open_master_temp.repository.TransferRepository;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulingController {

    private static long LAST_IDX = 0l;
    private final TransferRepository transferRepository;

    @Scheduled(cron = "* * * * * *")
    public void scheduleTaskUsingCronExpression() {

        List<TransferObject> transferObjects = transferRepository.findAllByIdxIsAfter(
            LAST_IDX);
        ListGroup list = new ListGroup(transferObjects);
        for (TransferObject transferObject : transferObjects) {
            udpSend(transferObject.getNumber().toString());
        }
        LAST_IDX = list.getNextIdx();
    }

    private void udpSend(final String msg) {
        try {
            InetAddress ip = InetAddress.getByName("127.0.0.1");
            DatagramSocket socket = new DatagramSocket();
            byte[] buf = msg.getBytes();
            int port = 7000;
            DatagramPacket p = new DatagramPacket(buf, buf.length, ip, port);
            socket.send(p);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
