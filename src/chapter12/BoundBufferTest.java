package chapter12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoundBufferTest {

    @Test
    void testIsEmptyWhenCreated(){
        BoundBuffer<Integer> buffer = new BoundBuffer<>(10);
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
    }

    @Test
    void testIsFullWhenNItemsAreInserted() throws InterruptedException {
        BoundBuffer<Integer> buffer = new BoundBuffer<>(10);

        for (int i = 0; i < 10; i++) {
            buffer.put(i);
        }

        assertTrue(buffer.isFull());
        assertFalse(buffer.isEmpty());
    }

    static final int LOCKUP_DETECT_TIMEOUT = 500;

    @Test
    void testTakeBlocksWhenEmpty(){
        final BoundBuffer<Integer> buffer = new BoundBuffer<>(10);
        Thread taker = new Thread(){
            @Override
            public void run() {
                try {
                    int unused = buffer.take();
                    fail("Should not have proceeded.");
                } catch (InterruptedException ignored) {
                    //success
                }
            }
        };
        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive());
        } catch (InterruptedException e) {
            fail();
        }

    }

}