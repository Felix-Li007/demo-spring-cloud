package demo.sport.zones.common.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

@Configuration
public class JacksonMapperConfig
{
    @Primary
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer()
    {
        return new Jackson2ObjectMapperBuilderCustomizer()
        {
            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder)
            {
                jacksonObjectMapperBuilder.deserializerByType(String.class, new StdScalarDeserializer<String>(String.class)
                {
                    @Override
                    public String deserialize(JsonParser jsonParser, DeserializationContext ctx)
                            throws IOException
                    {
                        //如果参数为空白则返回null
                        String strValue = jsonParser.getValueAsString();
                        if (ObjectUtils.isEmpty(strValue)) {
                            return null;
                        }
                        return strValue;
                    }
                });
            }
        };
    }

}
