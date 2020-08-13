package ua.telegrambot.weatherbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Weather {

    private String cityName;

    private String mainDescription;

    private Double minTemperature;

    private Double maxTemperature;

    private Double windSpeed;

    private Double humidity;

    private String icon;

    public String toString() {
        return "Місто:" + this.getCityName() + "\nПогода=" + this.getMainDescription() + "\nМінімальна температура=" + this.getMinTemperature() + "\nМаксимальна темература =" + this.getMaxTemperature() + "\nШвидкість вітру=" + this.getWindSpeed() + "\nВологість=" + this.getHumidity()+"%";
    }
}
