package skaro.pokedex.service.guild.settings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import skaro.pokedex.sdk.client.Language;

@Entity
@Table(name = "GUILD_SETTINGS")
public class GuildSettings {

	@Id
	@Column(name = "GUILD_ID")
	@Pattern(regexp = "^\\d+$")
	private String guildId;
	@Column(name = "PREFIX")
	private String prefix;
	@Column(name = "LANGUAGE")
	private Language language;
	
	public String getGuildId() {
		return guildId;
	}
	public void setGuildId(String guildId) {
		this.guildId = guildId;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	
}
