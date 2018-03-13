package DAO;

import Domain.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface PostDAO extends CrudRepository<Post, Long> {
    @Query(value = "SELECT * FROM Users", nativeQuery = true)
    List<Post> getPostList();

    @Modifying
    @Transactional
    @Query("delete from Post p where p.title = ?1")
    void deletePostByTitle(String firstName);

    @Query("select p from Post p where p.id = ?1")
    Post findByIdPost(Long pid);
}
