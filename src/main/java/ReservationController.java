import static spark.Spark.get;
import static spark.Spark.port;

public class ReservationController {
    public static void main(String arg[])
    {
        port(8080);
      get("/hello", (req, res) -> "Hello World");
    }
}
