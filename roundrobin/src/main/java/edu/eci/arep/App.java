package edu.eci.arep;
import static spark.Spark.*;

import java.io.IOException;
public class App 
{
    public static void main( String[] args )
    {
        port(getPort());
        staticFiles.location("/public");
        get("hello", (req,res) -> "Hello Docker!");
        get("/log",(req,res) -> {
            String val = req.queryParams("value");
            return HTTPConection.getLogs(val);
        });
    }
    // private static String logMessage(String val) throws IOException {
    //     //round robing
    //     return HTTPConection.getLogs(val);
    // }
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
