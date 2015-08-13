package blog.db.repository;

import org.springframework.stereotype.Repository;

import blog.model.AccountUser;

@Repository
public interface IUserRepository {
	
    public void createUser(AccountUser user);
    
    public AccountUser findById(String id);
    
    public AccountUser findByUserName(String userName);
     
    public void update(AccountUser user);
}
