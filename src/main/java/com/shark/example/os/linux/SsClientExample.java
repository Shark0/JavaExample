package com.shark.example.os.linux;


import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.future.ConnectFuture;
import org.apache.sshd.client.session.ClientSession;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SsClientExample {

    public void exec(
            String username, String password, String host, int port,
            String command) {

        SshClient client = SshClient.setUpDefaultClient();
        client.start();

        ConnectFuture connectFuture;
        try {
            connectFuture = client.connect(username, host, port);
            connectFuture.await(5, TimeUnit.SECONDS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ClientSession session = connectFuture.getClientSession()) {
            session.addPasswordIdentity(password);
            boolean isSuccess = session.auth().isSuccess();
            if (isSuccess) {
                session.createExecChannel(command);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        SsClientExample ssClientExample = new SsClientExample();
        ssClientExample.exec("root", "yryn6Ft2", "14.198.240.83", 22, "ls");
    }
}
