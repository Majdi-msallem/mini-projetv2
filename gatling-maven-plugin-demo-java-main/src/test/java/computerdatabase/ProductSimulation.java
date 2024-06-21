package computerdatabase;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;

public class ProductSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080") // Changez ceci avec l'URL de votre application
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")
            .userAgentHeader("Gatling");

    ScenarioBuilder createProductScenario = scenario("Create Product Scenario")
            .exec(
                    http("Create Product")
                            .post("/products")
                            .body(StringBody("{\"name\":\"Product 1\",\"description\":\"Description 1\",\"price\":100.0,\"storeId\":1}")).asJson()
                            .check(status().is(201))
            );

    ScenarioBuilder getAllProductsScenario = scenario("Get All Products Scenario")
            .exec(
                    http("Get All Products")
                            .get("/products")
                            .check(status().is(200))
            );

    {
        setUp(
                createProductScenario.injectOpen(
                        rampUsers(10).during(Duration.ofSeconds(10))
                ),
                getAllProductsScenario.injectOpen(
                        rampUsers(10).during(Duration.ofSeconds(10))
                )
        ).protocols(httpProtocol);
    }
}
