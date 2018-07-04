package br.com.sw.hdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableFeignClients
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class HddApplication {

    public static void main(String[] args) {
        SpringApplication.run(HddApplication.class, args);
    }

    @CacheEvict(allEntries = true, cacheNames = { "${caching.name.planets-all}",
            "${caching.name.planets-id}", "${caching.name.planets-name}", "${caching.name.planets-swapi}" })
    @Scheduled(fixedDelay = 30000)
    public void cacheEvict() {
    }
}
