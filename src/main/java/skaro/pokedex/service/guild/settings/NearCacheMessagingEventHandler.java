package skaro.pokedex.service.guild.settings;

import static skaro.pokedex.sdk.messaging.cache.CacheTopicMessagingConfiguration.CACHE_TOPIC_EXCHANGE_BEAN;
import static skaro.pokedex.sdk.messaging.cache.CacheTopicMessagingConfiguration.DISCORD_GUILD_SETTINGS_ROUTING_PATTERN_PREFIX;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import skaro.pokedex.sdk.messaging.cache.PrefixInvalidationMessage;

@Component
@RepositoryEventHandler(GuildSettings.class) 
public class NearCacheMessagingEventHandler {
	private final static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private TopicExchange topic;
	private RabbitTemplate template;
	
	public NearCacheMessagingEventHandler(@Qualifier(CACHE_TOPIC_EXCHANGE_BEAN) TopicExchange topic, RabbitTemplate template) {
		this.topic = topic;
		this.template = template;
	}

	@HandleAfterCreate
	@HandleAfterSave
	public void guildSettingsChange(GuildSettings settings) {
		String key = DISCORD_GUILD_SETTINGS_ROUTING_PATTERN_PREFIX;
		PrefixInvalidationMessage message = new PrefixInvalidationMessage();
		message.setGuildId(settings.getGuildId());
		
		try {
			template.convertAndSend(topic.getName(), key, message);
			LOG.info("Send prefix invalidation message for guild {}", settings.getGuildId());
		} catch(AmqpException e) {
			LOG.warn("Unable to send prefix invalidation for guild {}. This may result in stale data for clients' caches.", settings.getGuildId());
		}
	}
	
}
