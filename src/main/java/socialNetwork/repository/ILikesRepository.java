package socialNetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import socialNetwork.model.Likes;
import socialNetwork.model.Post;
import socialNetwork.model.User;


import java.util.List;

public interface ILikesRepository extends JpaRepository<Likes, Long> {
    @Query(nativeQuery = true, value = "select sum(score) from likes where post_id = :postid")
    Integer findSumLikes(@Param("postid") Long postid);
   @Query(nativeQuery = true, value = "select score from likes where post_id = :postid and user_id = :userid order by id desc limit 1")
    Integer findLastestLikes(@Param("postid") Long postid, @Param("userid") Long userid);
    Likes findByPostAndUser(Post post, User user);
    List<Likes> findAllByPost(Post post);
    @Query(nativeQuery = true, value = "select * from likes l where l.post_id in (select p.id from posts p where p.user_id = :userid)")
    List<Likes> findLikeNotify(@Param("userid") Long userid);
    @Query(nativeQuery = true, value = "select * from likes l where post_id = :postid and score = 1")
    List<Likes> findAllLikedPeopleByPost(@Param("postid") Long postid);
}
