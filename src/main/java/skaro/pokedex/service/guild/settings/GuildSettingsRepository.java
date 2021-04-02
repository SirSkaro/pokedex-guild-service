package skaro.pokedex.service.guild.settings;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = GuildSettingsRepository.PATH, collectionResourceRel = GuildSettingsRepository.RESOURCE_REL)
public interface GuildSettingsRepository extends PagingAndSortingRepository<GuildSettings, String> {
	public static final String PATH = "guild-settings";
	public static final String RESOURCE_REL = "guildSettings";
	
}
