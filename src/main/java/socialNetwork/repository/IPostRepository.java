package socialNetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import socialNetwork.model.Post;
import socialNetwork.model.User;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    Iterable<Post> findByUserOrderByPostTimeDesc(User user);
    Iterable<Post> findAllByOrderByPostTimeDesc();
}
