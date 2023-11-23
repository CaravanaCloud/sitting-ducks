package ducks.mem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("leak")
public class LeakyResource {
    static final List<Integer> leak = new ArrayList<>();
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSorted(@QueryParam("x") @DefaultValue("1") int x){        
        for (var i = 0; i < x; i++) {
            var y = ThreadLocalRandom.current().nextInt();
            leak.add(y);
        }
        var result = leak.stream()
            .sorted()
            .map(Object::toString)
            .collect(Collectors.joining(", "));
        return result;
    }
}
