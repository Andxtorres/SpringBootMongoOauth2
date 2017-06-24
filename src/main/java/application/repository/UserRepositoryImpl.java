package application.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import application.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    MongoTemplate mongoTemplate;
	@Override
	public void insetUser(User user) {
		mongoTemplate.insert(user);
	}
	@Override
	public User findUserByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("firstName").is(name));
		return mongoTemplate.findOne(query, User.class);
	}
	@Override
	public void updateUser(User user) {
		mongoTemplate.save(user);
		
	}

}
