package it.mdotm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class ExapiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ExapiApplication.class, args);
        var buildProp = applicationContext.getBean(BuildProperties.class);

        log.info("Free Memory {} MB", toMb(Runtime.getRuntime().freeMemory()));
        log.info("Heap size {} MB", toMb(Runtime.getRuntime().totalMemory()));
        log.info("Max Heap size {} MB", toMb(Runtime.getRuntime().maxMemory()));
        log.info("MDOTM API version: {}", buildProp.getVersion());

    }

    private static BigDecimal toMb(Long kb) {
        return BigDecimal.valueOf(kb).divide(BigDecimal.valueOf(1024 * 1024L), 2,
                RoundingMode.FLOOR);
    }

}
