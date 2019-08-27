


import com.google.gson.annotations.SerializedName;

public class BaseWeather {

    @SerializedName("main")
    public WeatherData main;

    @SerializedName("name")
    public String name;

    public WeatherData getMain() {
        return main;
    }

    public String getName() {
        return name;
    }
}