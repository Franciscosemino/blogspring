package DAO;

import Domain.Group;
import Domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDAO extends CrudRepository<Group, Long> {

    @Query(value = "SELECT * FROM groups WHERE name = ?1",nativeQuery = true)
    Group findByName(String name);

    @Query(value = "SELECT * FROM groups WHERE gid = ?1", nativeQuery = true)
    Group findByIdGroup(Long id);
}
