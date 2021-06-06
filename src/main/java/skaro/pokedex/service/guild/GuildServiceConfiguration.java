package skaro.pokedex.service.guild;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import skaro.pokedex.sdk.messaging.cache.NearCacheTopicMessagingConfiguration;

@Configuration
@Import(NearCacheTopicMessagingConfiguration.class)
public class GuildServiceConfiguration {
	
}
