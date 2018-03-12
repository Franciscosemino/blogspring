package DAO;


import Domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE uname = ?1",nativeQuery = true)
    User findByName(String name);


    @Query(value = "SELECT * FROM users WHERE uid = ?1", nativeQuery = true)
    User findByIdUser(Long id);

}
