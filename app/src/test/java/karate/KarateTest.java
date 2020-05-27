package karate;

import com.intuit.karate.junit5.Karate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = dev.schertel.cq.CentralQueueApplication.class)
class KarateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ConfigurableEnvironment env;

    @Karate.Test
    Karate testKarate() {
        System.setProperty("karate.env", String.join(",", env.getActiveProfiles()));
        System.setProperty("app.server.https", Boolean.FALSE.toString());
        System.setProperty("app.server.port", String.valueOf(port));
        System.setProperty("app.server.domain", "localhost");
        return Karate.run().tags("~@ignore").relativeTo(getClass());
    }
}
