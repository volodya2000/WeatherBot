import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    private String cityName;

    private String mainDescription;

    private Double minTemperature;

    private Double maxTemperature;

    private Double windSpeed;

    private Double humidity;

    private String icon;
}
