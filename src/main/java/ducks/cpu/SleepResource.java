package ducks.cpu;

import java.math.BigInteger;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/sleep")
public class SleepResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSleep(@QueryParam("x") @DefaultValue("1") Long x){        
        try {
            Thread.sleep(x * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        var result = "Sleeping for " + x + " seconds";
        return result;
    }
}
