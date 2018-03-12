package DAO;

import Domain.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDAO extends CrudRepository<Group, Long> {

    @Query(value = "SELECT * FROM groups WHERE name = ?1",nativeQuery = true)
    Group findByName(String name);

}
