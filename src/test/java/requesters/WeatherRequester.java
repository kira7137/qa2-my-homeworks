package requesters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.WeatherResponse;
import org.springframework.web.client.RestTemplate;

public class WeatherRequester {
    //obrashaemsja na server za pogodoj cherez API
    //requester - zapros infi s servera i poluchenie dannih
    //ispolzuem gotovij kod iz biblioteki- SpringWeb (chastj framework'a Spring), google-mvn spring-web
    //biblioteka Jackson-- kot.otvechaet za Mapping- iz ljubogo objecta sozdatj Json, i razlozitj po modeli i objectam
    //Jackson Databind
    //Jackson Core

    private final String PREFIX = "https://samples.openweathermap.org/data/2.5/weather?id=";
    private final String POSTFIX = "&appid=b1b15e88fa797225412429c1c50c122a1";


    public WeatherResponse getWeatherData(long id) throws JsonProcessingException {
        String url = PREFIX + id + POSTFIX;

        //We are using spring-web to make queries and get response as String
        RestTemplate restTemplate = new RestTemplate();    // sozdanie kopii eksemplara objecta
        String json = restTemplate.getForEntity(url, String.class).getBody();  //sam zapros GET + parametri + puluchenie tolko tela zaprosa

         //poluchitj dannie v vide nabora objectov, v vide modeli
        //We are using Jackson to get objecs with data as model from JSON (to convert/map)
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, WeatherResponse.class);
    }
}
