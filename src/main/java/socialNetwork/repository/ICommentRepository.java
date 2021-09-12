package socialNetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import socialNetwork.model.Comment;
import socialNetwork.model.Post;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    Iterable<Comment> findByPostOrderByCommentTime(Post post);
}
