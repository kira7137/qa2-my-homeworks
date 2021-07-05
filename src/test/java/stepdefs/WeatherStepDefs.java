package stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.WeatherResponse;
import org.junit.jupiter.api.Assertions;
import requesters.WeatherRequester;

import java.math.BigDecimal;
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

    @And("weather is:")
    public void check_weather(Map<String, String> weather) {
        Assertions.assertEquals(weather.get("id"), response.getWeather().getId(), "Incorrect id");
        Assertions.assertEquals(weather.get("main"), response.getWeather().getMain(), "Incorrect main");
        Assertions.assertEquals(weather.get("description"), response.getWeather().getDescription(), "Incorrect description");
        Assertions.assertEquals(weather.get("icon"), response.getWeather().getIcon(), "Incorrect icon");
    }


    @And("base is (.*)")
    public void check_base(String base) {
        Assertions.assertEquals(base, response.getBase(), "Incorrect base");
    }

    @And("main is:")
    public void check_main(Map<String, String> params) {
    Assertions.assertEquals(params.get("temp"), response.getMain().getTemp(), "Incorrect temp");
    Assertions.assertEquals(params.get("pressure"), response.getMain().getPressure(), "Incorrect pressure");
    Assertions.assertEquals(params.get("humidity"), response.getMain().getHumidity(), "Incorrect humidity");
    Assertions.assertEquals(params.get("temp_min"), response.getMain().getTemp_min(), "Incorrect temp_min");
    Assertions.assertEquals(params.get("temp_max"), response.getMain().getTemp_max(), "Incorrect temp_max");
    }

    @And("visibility is ([0-9*])")
    public void visibility_data_is(BigDecimal visibility) {
        Assertions.assertEquals(visibility, response.getVisibility(), "Incorrect visibility");
    }

    @And("wind is:")
    public void check_wind(Map<String, Double> wind) {
        Assertions.assertEquals(wind.get("speed"), response.getWind().getSpeed(), "Incorrect speed");
        Assertions.assertEquals(wind.get("deg"), response.getWind().getDeg(), "Incorrect deg");
    }

    @And("clouds are:")
    public void check_clouds(Map<String, Integer> clouds) {
        Assertions.assertEquals(clouds.get("all"), response.getClouds().getAll(), "Incorrect clouds");
    }

    @And("dt is ([0-9*])")
    public void dt_is(BigDecimal dt) {
        Assertions.assertEquals(dt, response.getDt(), "Incorrect dt");
    }

    @And("sys are:")
    public void check_sys(Map<String, String> sys) {
        Assertions.assertEquals(sys.get("type"), response.getSys().getType(), "Incorrect type");
        Assertions.assertEquals(sys.get("id"), response.getSys().getId(), "Incorrect id");
        Assertions.assertEquals(sys.get("message"), response.getSys().getMessage(), "Incorrect message");
        Assertions.assertEquals(sys.get("country"), response.getSys().getCountry(), "Incorrect country");
        Assertions.assertEquals(sys.get("sunrise"), response.getSys().getSunrise(), "Incorrect sunrise");
        Assertions.assertEquals(sys.get("sunset"), response.getSys().getSunset(), "Incorrect sunset");
    }
    @And("id is ([0-9*])")
    public void id_data_is(BigDecimal id) {
        Assertions.assertEquals(id, response.getId(), "Incorrect id");
    }

    @And("name is (.*)")
    public void name_is(String name) {
        Assertions.assertEquals(name, response.getName(), "Incorrect name");
    }

    @And("cod is ([0-9*])")
    public void cod_is(int cod) {
        Assertions.assertEquals(cod, response.getCod(), "Incorrect cod");
    }
}
