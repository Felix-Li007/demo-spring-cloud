package demo.sport.zones.common.config;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.protocol.ProtocolVersion;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;

/**
 * manually specifying RESP2
 *
 * @author koushenglong
 * @since 1.0.0
 */
@Configuration
public class SpringRedisConfig implements LettuceClientConfigurationBuilderCustomizer
{

    @Override
    public void customize(LettuceClientConfiguration.LettuceClientConfigurationBuilder clientConfigurationBuilder)
    {
        // manually specifying RESP2
        clientConfigurationBuilder.clientOptions(ClientOptions.builder()
                .protocolVersion(ProtocolVersion.RESP2)
                .build());
    }

}
