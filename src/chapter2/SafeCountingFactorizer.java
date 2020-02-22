package chapter2;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SafeCountingFactorizer implements Servlet {


    private final AtomicLong counter = new AtomicLong();

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        counter.incrementAndGet();
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(res, factors);
    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[0];
    }

    private BigInteger extractFromRequest(ServletRequest req) {
        return null;
    }

    private void encodeIntoResponse(ServletResponse response, BigInteger[] factors) {

    }

    public long getCounter(){
        return counter.get();
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

}
