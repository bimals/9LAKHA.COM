package blog.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import blog.model.AccountUser;

@Repository
@Qualifier("userRepository")
public class UserRepository implements IUserRepository {

    @Autowired private MongoOperations mongoOps;
    private static final String USER_COLLECTION = "User";
    
	public void createUser(AccountUser user) {
		this.mongoOps.insert(user, USER_COLLECTION);
	}
	public AccountUser findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return this.mongoOps.findOne(query, AccountUser.class, USER_COLLECTION);
	}
	public AccountUser findByUserName(String userName) {
        Query query = new Query(Criteria.where("userName").is(userName));
        return this.mongoOps.findOne(query, AccountUser.class, USER_COLLECTION);
	}
	public void update(AccountUser user) {
		// TODO Auto-generated method stub	
	}
	
}
