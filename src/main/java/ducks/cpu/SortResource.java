package ducks.cpu;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

public class SortResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSorted(@QueryParam("x") @DefaultValue("1") int x){        
        var ys = new ArrayList<Integer>(x);
        for (var i = 0; i < x; i++) {
            var y = ThreadLocalRandom.current().nextInt();
            ys.add(y);
                
        var result = ys.stream()
            .sorted()
            .map(Object::toString)
            .collect(Collectors.joining(", "));
        return result;
    }
}
