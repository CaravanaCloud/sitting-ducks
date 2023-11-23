package ducks.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/file")
public class FileResource {

    @Path("/read")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRead(@QueryParam("x") @DefaultValue("1") int x) {
        var file = new File("/dev/urandom");
        var ys = new byte[x];
        try (var is = new FileInputStream(file)) {
            is.read(ys);
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e.getMessage());
            e.printStackTrace();
        }
        var result = Arrays.toString(ys);
        return result;
    }

    @Path("/write")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getWrite(@QueryParam("x") @DefaultValue("1") int x) {
        var ys = new ArrayList<Integer>(x);
        try (var fw = new FileWriter(
                File.createTempFile("duck","txt"));
             var writer = new PrintWriter(fw)) {
            for (var i = 0; i < x; i++) {
                writer.println(i);
                ys.add(i);
            }
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
        var result = ys.stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        return result;
    }

}
