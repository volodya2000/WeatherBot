package ua.telegrambot.weatherbot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
@PropertySource("classpath:weather.properties")
@Getter
@Setter
@NoArgsConstructor
public class JsonMapper {

    @Value("${weather.icon}")
    private String iconApi;

    private  String apiUrl;

    private String token;

    private String celsius;

    private Weather weather;

    @Autowired
    public JsonMapper(Weather weather, @Value("${weather.api}") String apiUrl,@Value("${weather.token}") String token,
                      @Value("${weather.celsius}")String celsius) {
        this.weather = weather;
        this.apiUrl=apiUrl;
        this.token=token;
        this.celsius=celsius;
    }

    public Weather getWeather(String cityName) throws IOException
    {
            Weather weather= new Weather();

            String weatherData = new JSONObject(IOUtils.toString(new URL(apiUrl+cityName+celsius+token), Charset.forName("UTF-8"))).toString();
            JSONObject weatherJson=new JSONObject(weatherData);

            weather.setCityName(weatherJson.getString("name"));

            JSONObject mainJson=weatherJson.getJSONObject("main");

            weather.setMinTemperature(mainJson.getDouble("temp_min"));

            weather.setMaxTemperature(mainJson.getDouble("temp_max"));

            weather.setHumidity(mainJson.getDouble("humidity"));

            JSONArray weatherArray=weatherJson.getJSONArray("weather");

            weather.setMainDescription(weatherArray.getJSONObject(0).getString("description"));

            JSONObject windJson=weatherJson.getJSONObject("wind");

            weather.setWindSpeed(windJson.getDouble("speed"));

            weather.setIcon(iconApi+weatherArray.getJSONObject(0).getString("icon")+".png");

        return weather;
    }
}
