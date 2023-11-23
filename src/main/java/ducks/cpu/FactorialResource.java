package ducks.cpu;

import java.math.BigInteger;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/factorial")
public class FactorialResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public BigInteger getFactorial(@QueryParam("x") @DefaultValue("1") int x) {
        var result = BigInteger.ONE;
        for (var i = 2; i <= x; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
