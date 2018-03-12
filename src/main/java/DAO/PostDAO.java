package DAO;

import Domain.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostDAO extends CrudRepository<Post, Long> {
    @Query(value = "SELECT * FROM Users", nativeQuery = true)
    List<Post> getPostList();
}
