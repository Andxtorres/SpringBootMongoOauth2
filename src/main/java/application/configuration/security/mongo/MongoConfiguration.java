package application.configuration.security.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {
	@Override
	@Bean
	public CustomConversions customConversions() {
	List<OAuth2AuthenticationReadConverter> converterList = new ArrayList<>();
	OAuth2AuthenticationReadConverter converter = new OAuth2AuthenticationReadConverter();
	converterList.add(converter);
	return new CustomConversions(converterList);
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "naurooJobs";
	}

	@Override
	public Mongo mongo() throws Exception {
		// TODO Auto-generated method stub
		return new MongoClient("localhost");
	}
}
