package stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Weather;
import model.WeatherResponse;
import org.junit.jupiter.api.Assertions;
import requesters.WeatherRequester;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

public class WeatherStepDefs {
    private long cityId;
    private WeatherResponse response;

    @Given("city ID: {long}")

    public void set_city_id(long cityId) {
        this.cityId = cityId;

    }

    @When("we are requesting weather data")
    public void request_weather() throws JsonProcessingException {
            //obrashaemsja na server za pogodoj cherez API
            //requester - zapros infi s servera
        WeatherRequester requester = new WeatherRequester(); //vizivaem requester
        response = requester.getWeatherData(cityId);
    }

    @Then("coordinates are:")
    public void check_coords(Map<String, Double> coords) {
        Assertions.assertEquals(coords.get("lon"), response.getCoord().getLon(), "Incorrect coord lon");
        Assertions.assertEquals(coords.get("lat"), response.getCoord().getLat(), "Incorrect coord lat");

    }

//    @Then("weather is:")
//    public void check_weather(Map<String, String> weathers) {
//        Assertions.assertEquals(Integer.parseInt(weathers.get("id")), response.getWeathers().get(0).getId(), "Incorrect id");
//        Assertions.assertEquals(weathers.get("main"), response.getWeathers().get(1).getMain(), "Incorrect main");
//        Assertions.assertEquals(weathers.get("description"), response.getWeathers().get(2).getDescription(), "Incorrect desc");
//        Assertions.assertEquals(weathers.get("icon"), response.getWeathers().get(3).getIcon(), "Incorrect icon");
//    }

    @Then("weathers are:")
    public void check_weathers(DataTable dataTable) {
        List<Map<String, String>> weathers = dataTable.asMaps();
        Assertions.assertEquals(weathers.size(), response.getWeathers().size(), "Wrong...");

        for (int i = 0; i < weathers.size(); i++) {
            Map<String, String> expectedWeather = weathers.get(i);
            Weather actualWeather = response.getWeathers().get(i);
            Assertions.assertEquals(Long.parseLong(expectedWeather.get("id")), actualWeather.getId(), "Error message");
            Assertions.assertEquals(expectedWeather.get("main"), actualWeather.getMain(), "Error message");
            Assertions.assertEquals(expectedWeather.get("description"), actualWeather.getDescription(), "Error message");
            Assertions.assertEquals(expectedWeather.get("icon"), actualWeather.getIcon(), "Error message");
        }
    }

    @Then("base is {string}")
    public void check_base(String base) {
        Assertions.assertEquals(base, response.getBase(), "Incorrect base");
    }

    @Then("main is:")
    public void check_main(Map<String, String> params) {
    Assertions.assertEquals(Double.parseDouble(params.get("temp")), response.getMain().getTemp(), "Incorrect temp");
    Assertions.assertEquals(Integer.parseInt(params.get("pressure")), response.getMain().getPressure(), "Incorrect pressure");
    Assertions.assertEquals(Integer.parseInt(params.get("humidity")), response.getMain().getHumidity(), "Incorrect humidity");
    Assertions.assertEquals(Double.parseDouble(params.get("temp_min")), response.getMain().getTemp_min(), "Incorrect temp_min");
    Assertions.assertEquals(Double.parseDouble(params.get("temp_max")), response.getMain().getTemp_max(), "Incorrect temp_max");
    }

    @Then("visibility is {int}")
    public void visibility_data_is(int visibility) {
        Assertions.assertEquals(visibility, response.getVisibility(), "Incorrect visibility");
    }

    @Then("wind is:")
    public void check_wind(Map<String, Double> wind) {
        Assertions.assertEquals(wind.get("speed"), response.getWind().getSpeed(), "Incorrect speed");
        Assertions.assertEquals(wind.get("deg"), response.getWind().getDeg(), "Incorrect deg");
    }

    @Then("clouds are:")
    public void check_clouds(Map<String, Integer> clouds) {
        Assertions.assertEquals(clouds.get("all"), response.getClouds().getAll(), "Incorrect clouds");
    }

    @Then("dt is {long}")
    public void dt_is(long dt) {
        Assertions.assertEquals(dt, response.getDt(), "Incorrect dt");
    }

    @Then("sys are:")
    public void check_sys(Map<String, String> sys) {
        Assertions.assertEquals(Integer.parseInt(sys.get("type")), response.getSys().getType(), "Incorrect type");
        Assertions.assertEquals(Long.parseLong(sys.get("id")), response.getSys().getId(), "Incorrect id");
        Assertions.assertEquals(Double.parseDouble(sys.get("message")), response.getSys().getMessage(), "Incorrect message");
        Assertions.assertEquals(sys.get("country"), response.getSys().getCountry(), "Incorrect country");
        Assertions.assertEquals(Long.parseLong(sys.get("sunrise")), response.getSys().getSunrise(), "Incorrect sunrise");
        Assertions.assertEquals(Long.parseLong(sys.get("sunset")), response.getSys().getSunset(), "Incorrect sunset");


        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochSecond(response.getSys().getSunrise()), ZoneId.systemDefault());
        System.out.println(date);

        LocalDateTime dateTime1 = LocalDateTime.ofInstant(Instant.ofEpochSecond(response.getSys().getSunrise()), ZoneId.systemDefault());
        System.out.println(dateTime1);

    }

    @Then("id is {long}")
    public void id_data_is(long id) {
        Assertions.assertEquals(id, response.getId(), "Incorrect id");
    }

    @Then("name is {string}")
    public void name_is(String name) {
        Assertions.assertEquals(name, response.getName(), "Incorrect name");
    }

    @Then("cod is {int}")
    public void cod_is(int cod) {
        Assertions.assertEquals(cod, response.getCod(), "Incorrect cod");
    }
}
