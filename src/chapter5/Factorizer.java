package chapter5;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

public class Factorizer implements Servlet {
    private final Computable<BigInteger,BigInteger[]> function = new Computable<BigInteger, BigInteger[]>() {
        @Override
        public BigInteger[] compute(BigInteger args) {
            return factor(args);
        }
    };

    private final Memorizer<BigInteger,BigInteger[]> cache = new Memorizer<>(function);

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

        encodeIntoResponse(res, cache.compute(i));
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
