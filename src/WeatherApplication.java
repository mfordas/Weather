import com.google.gson.*;

import java.io.IOException;
import java.util.Scanner;



public class WeatherApplication {

    public static void main(String[] args) {
        String cityToCheck;
        String nextCityDecision;
        HttpConnection newConnection = new HttpConnection();
        Gson gson = new Gson();

        do {
            System.out.println("Podaj nazwę miasta dla którego chcesz sprawdzić pogodę");

            cityToCheck = readCity();
            boolean isError = false;
            try {
                String weatherData = newConnection.callWeatherForCity(cityToCheck);
                BaseWeather baseWeatherObject = gson.fromJson(weatherData, BaseWeather.class);

                showInfoAboutWeather(baseWeatherObject);
            } catch (IOException e) {
                isError = true;
                showErrorMessages();
            }

            nextCityDecision = "t";
            if (isError) {
                continue;
            }

            System.out.println("Sprawdzic pogode dla nastepnego miasta? T/N");
            nextCityDecision = readNextCity();

        } while (nextCityDecision.equalsIgnoreCase("t"));

        System.out.println("To nara szapra");

    }

    private static void showErrorMessages() {
        System.out.println("Błąd! Nie ma takiego miasta w naszej bazie!");
        System.out.println("Wprowadz inne miasto");
    }

    private static void showInfoAboutWeather(BaseWeather baseWeatherObject) {
        System.out.println("Pogoda dla: " + baseWeatherObject.getName());
        System.out.println("Temperatura: " + baseWeatherObject.getMain().getTemp() + "°C");
        System.out.println("Temperatura maksymalna: " + baseWeatherObject.getMain().getTempMax() + "°C");
        System.out.println("Temperatura minimalna: " + baseWeatherObject.getMain().getTempMin() + "°C");
        System.out.println("Cisnienie: " + baseWeatherObject.getMain().getPressure() + "hPa");
        System.out.println("Wilgotnosc: " + baseWeatherObject.getMain().getHumidity() + "%");
    }

    private static String readCity() {
        return new Scanner(System.in).nextLine();
    }

    private static String readNextCity() {
        return new Scanner(System.in).nextLine();
    }


}
