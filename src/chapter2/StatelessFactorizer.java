package chapter2;

import net.jcip.annotations.NotThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

@NotThreadSafe
public class StatelessFactorizer implements Servlet {
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

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
