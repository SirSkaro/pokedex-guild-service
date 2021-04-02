package skaro.pokedex.service.guild;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import skaro.pokedex.sdk.messaging.cache.CacheTopicMessagingConfiguration;

@Configuration
@Import(CacheTopicMessagingConfiguration.class)
public class GuildServiceConfiguration {
	
}
