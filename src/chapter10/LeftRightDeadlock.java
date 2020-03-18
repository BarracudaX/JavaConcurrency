package chapter10;

import java.util.concurrent.CountDownLatch;

/**
 * A simple example of potential deadlock.
 */
public class LeftRightDeadlock {

    private final Object leftLock = new Object();
    private final Object rightLock = new Object();

    public void leftRight() throws InterruptedException {
        synchronized (leftLock) {
            Thread.sleep(400);
            synchronized (rightLock) {
                System.out.println("Left-Right");
            }
        }
    }

    public void rightLeft() throws InterruptedException {
        synchronized (rightLock) {
            Thread.sleep(300);
            synchronized (leftLock) {
                System.out.println("Right-Left");
            }
        }
    }

    /**
     * Test method that causes two thread to block.
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        LeftRightDeadlock deadlock = new LeftRightDeadlock();
        CountDownLatch latch = new CountDownLatch(1);

        Thread threadA = new Thread(() -> {
            try {
                latch.await();
                deadlock.leftRight();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        threadA.start();

        Thread threadB = new Thread(() -> {
            try {
                latch.await();
                deadlock.rightLeft();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        });

        threadB.start();

        latch.countDown();

    }

}
