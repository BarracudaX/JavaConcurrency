package chapter5;

import java.util.concurrent.CountDownLatch;

public class TestLatch {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch serverLatch = new CountDownLatch(1);
        CountDownLatch fileLatch = new CountDownLatch(1);
        CountDownLatch gate = new CountDownLatch(1);
        CountDownLatch services = new CountDownLatch(3);

        Service fileService = new FileSystemService(fileLatch);
        Service serverService = new ServerService(fileLatch,serverLatch);
        Service clientService = new ClientService(serverLatch);


        Thread fileSystemServiceThread = new Thread(() -> {
            try {
                gate.await();
                fileService.start();
                services.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread serverServiceThread = new Thread(() -> {
            try {
                gate.await();
                serverService.start();
                services.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread clientServiceThread = new Thread(() -> {
            try {
                gate.await();
                clientService.start();
                services.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });


        clientServiceThread.start();
        serverServiceThread.start();
        fileSystemServiceThread.start();

        gate.countDown();//allow threads to start

        services.await();//wait til all services are started

        System.out.println("Finish.");
    }

}
