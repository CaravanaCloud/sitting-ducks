package ducks.cpu;

import java.math.BigInteger;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/fibo")
public class FibonacciResource {
    @GET
    @Path("recursive")
    @Produces(MediaType.TEXT_PLAIN)
    public BigInteger getFiboRecursive(@QueryParam("x") @DefaultValue("1") BigInteger x) {
        var result = BigInteger.ONE;
        if (x.compareTo(BigInteger.ONE) > 0) {
            var x2 = getFiboRecursive(x.subtract(BigInteger.TWO));
            var x1 = getFiboRecursive(x.subtract(BigInteger.ONE));
            result = x1.add(x2);
        }
        return result;
    }

    @GET
    @Path("iterative")
    @Produces(MediaType.TEXT_PLAIN)
    public BigInteger getFiboIterative(@QueryParam("x") @DefaultValue("1") BigInteger x) {
        var result = BigInteger.ONE;
        var x1 = BigInteger.ONE;
        var x2 = BigInteger.ONE;
        for (var i = BigInteger.valueOf(2); i.compareTo(x) <= 0; i = i.add(BigInteger.ONE)) {
            result = x1.add(x2);
            x2 = x1;
            x1 = result;
        }        
        return result;
    }
}
