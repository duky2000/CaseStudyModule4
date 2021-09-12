package socialNetwork.service;

import socialNetwork.model.Comment;
import socialNetwork.model.Post;

public interface ICommentService extends IGeneralService<Comment>{
    Iterable<Comment> findByPostOrderByCommentTime(Post post);
}
