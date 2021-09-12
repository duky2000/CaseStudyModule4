package socialNetwork.service;

import org.springframework.data.repository.query.Param;
import socialNetwork.model.Likes;
import socialNetwork.model.Post;
import socialNetwork.model.User;


import java.util.List;

public interface ILikesService extends IGeneralService<Likes>{
    Integer findSumLikes(@Param("postid") Long postid);
    Integer findLastestLikes(Long postid, Long userid);
    Likes findByPostAndUser(Post post, User user);
    List<Likes> findAllByPost(Post post);
    List<Likes> findLikeNotify(@Param("userid") Long userid);
    List<Likes> findAllLikedPeopleByPost(@Param("postid") Long postid);
}
