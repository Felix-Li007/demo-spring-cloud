package demo.sport.zones;

import com.hrms.frame.cache.annotation.EnableHrmsCaching;
import com.hrms.frame.sequence.EnableHrmsSequence;
import demo.sport.zones.common.constant.RedisCacheEnum;
import demo.sport.zones.common.constant.ZonesSequenEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableFeignClients
@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
@EnableHrmsCaching(RedisCacheEnum.class)
@EnableHrmsSequence(ZonesSequenEnum.class)
@ComponentScan(basePackages = {"com.hrms.sport.zones", "com.hrms.core"})
public class SportZonesServer
{

    public static void main(String[] args)
    {
        SpringApplication.run(SportZonesServer.class, args);
    }
}
