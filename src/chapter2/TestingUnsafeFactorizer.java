package chapter2;

import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;

import static org.mockito.Mock.*;

public class TestingUnsafeFactorizer {

    public static void main(String[] args) throws InterruptedException {
        UnsafeCountingFactorizer unsafeCountingFactorizer = new UnsafeCountingFactorizer();
        ServletRequest request = Mockito.mock(ServletRequest.class);
        ServletResponse response = Mockito.mock(ServletResponse.class);

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    unsafeCountingFactorizer.service(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Inner thread : "+unsafeCountingFactorizer.getCounter());
            }
        });

        thread.start();

        for (int i = 0; i < 1000; i++) {
            try {
                unsafeCountingFactorizer.service(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Outer thread : "+unsafeCountingFactorizer.getCounter());
        }


    }

}
