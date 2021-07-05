package stepdefs;

import io.cucumber.java.en.Given;

public class HelloStepDefs {  //class dlja svjazki shagov s java kodom
    @Given("say hello to world")  //annotacija dlja nazvanija kak shag budet nazivatja na obichnom jazike
    public void say_hello() { //metod dlja odnogo shaga
        System.out.println("Hello, world!!!");
    }
    @Given("say {string} also")
    public void print_custom_message(String message) {
        System.out.println(message);
    }
}
