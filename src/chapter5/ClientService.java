package chapter5;

import java.util.concurrent.CountDownLatch;

public class ClientService implements Service {

    private final CountDownLatch serverService;

    public ClientService(CountDownLatch serverService) {
        this.serverService = serverService;
    }

    @Override
    public void start() {
        try {
            serverService.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ;
        }

        System.out.println("Client service started");
    }
}
