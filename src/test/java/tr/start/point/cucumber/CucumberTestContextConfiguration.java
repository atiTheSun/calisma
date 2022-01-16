package tr.start.point.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import tr.start.point.IntegrationTest;

@CucumberContextConfiguration
@IntegrationTest
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
