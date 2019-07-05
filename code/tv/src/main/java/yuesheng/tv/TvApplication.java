package yuesheng.tv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})

public class TvApplication {

    public static void main(String[] args) {
        SpringApplication.run(TvApplication.class, args);
    }

}
