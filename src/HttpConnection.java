import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;


public class HttpConnection {

    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String CHARSET = "UTF-8";
    private static final String API_KEY = "f051a3a6eaeb0d3041fa073c40a73a0c";


    public String callWeatherForCity(String city) throws IOException {
        String query = String.format("q=%s", URLEncoder.encode(city, CHARSET));
        URLConnection connection = new URL(API_URL + "?" + query + "&units=metric" + "&appid=" + API_KEY).openConnection();
        connection.setRequestProperty("Accept-charset", CHARSET);

        InputStream response = connection.getInputStream();
        return convertStreamToString(response);
    }

    private static String convertStreamToString(InputStream is) {
        Scanner scan = new Scanner(is).useDelimiter("\\A");
        return scan.hasNext() ? scan.next() : "";
    }
}
