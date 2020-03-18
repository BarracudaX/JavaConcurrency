package chapter5;

import java.util.concurrent.CountDownLatch;

public class FileSystemService implements Service {

    private final CountDownLatch myService;

    public FileSystemService(CountDownLatch myService) {
        this.myService = myService;
    }

    @Override
    public void start() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ;
        }
        System.out.println("File System Service started.");
        myService.countDown();
    }

}
