package chapter5;

import java.util.concurrent.CountDownLatch;

public class ServerService implements Service {

    private final CountDownLatch fileSystemService;

    private final CountDownLatch myService;

    public ServerService(CountDownLatch fileSystemService, CountDownLatch myService) {
        this.fileSystemService = fileSystemService;
        this.myService = myService;
    }

    @Override
    public void start() {
        try {
            this.fileSystemService.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ;
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Server service started.");

        myService.countDown();

    }

}
