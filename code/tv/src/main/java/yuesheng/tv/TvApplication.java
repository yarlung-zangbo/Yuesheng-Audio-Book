package yuesheng.tv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication()
@EnableEurekaClient
public class TvApplication {
    public static void main(String[] args) {
        SpringApplication.run(TvApplication.class, args);
    }

}
