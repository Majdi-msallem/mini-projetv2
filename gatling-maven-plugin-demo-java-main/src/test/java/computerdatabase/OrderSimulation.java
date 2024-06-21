package computerdatabase;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;

public class OrderSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080") // Changez ceci avec l'URL de votre application
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")
            .userAgentHeader("Gatling");

    ScenarioBuilder createOrderScenario = scenario("Create Order Scenario")
            .exec(
                    http("Create Order")
                            .post("/orders")
                            .body(StringBody("{\"productId\":1,\"quantity\":3}")).asJson()
                            .check(status().is(201))
            );

    ScenarioBuilder getAllOrdersScenario = scenario("Get All Orders Scenario")
            .exec(
                    http("Get All Orders")
                            .get("/orders")
                            .check(status().is(200))
            );

    {
        setUp(
                createOrderScenario.injectOpen(
                        rampUsers(10).during(Duration.ofSeconds(10))
                ),
                getAllOrdersScenario.injectOpen(
                        rampUsers(10).during(Duration.ofSeconds(10))
                )
        ).protocols(httpProtocol);
    }
}
