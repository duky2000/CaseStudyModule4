package socialNetwork.service;


import socialNetwork.model.Post;
import socialNetwork.model.User;

public interface IPostService extends IGeneralService<Post>{
    Iterable<Post> findByUserOrderByPostTimeDesc(User user);
    Iterable<Post> findAllByOrderByPostTimeDesc();
}
