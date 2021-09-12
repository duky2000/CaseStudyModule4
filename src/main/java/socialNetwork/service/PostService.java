package socialNetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialNetwork.model.Post;
import socialNetwork.model.User;
import socialNetwork.repository.IPostRepository;

import java.util.Optional;

@Service
public class PostService implements IPostService {
    @Autowired
    IPostRepository postRepository;
    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Iterable<Post> findByUserOrderByPostTimeDesc(User user) {
        return postRepository.findByUserOrderByPostTimeDesc(user);
    }

    @Override
    public Iterable<Post> findAllByOrderByPostTimeDesc() {
        return postRepository.findAllByOrderByPostTimeDesc();
    }


}
