package chapter2;

import net.jcip.annotations.GuardedBy;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

public class SafeCacheFactorizer implements Servlet {

    @GuardedBy("this")
    private volatile BigInteger lastNumberRequested = null;
    @GuardedBy("this")
    private volatile BigInteger[] factorsOfTheLastNumberRequested = null;

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        synchronized (this) {
            if (i.equals(lastNumberRequested)) {
                encodeIntoResponse(res,factorsOfTheLastNumberRequested);
            }
        }

        BigInteger[] factors = factor(i);
        synchronized (this) {
            lastNumberRequested = i ;
            factorsOfTheLastNumberRequested = factors;
        }
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


    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

}
