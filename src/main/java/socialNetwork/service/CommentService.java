package socialNetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialNetwork.model.Comment;
import socialNetwork.model.Post;
import socialNetwork.repository.ICommentRepository;


import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Autowired
    ICommentRepository commentRepository;
    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> findByPostOrderByCommentTime(Post post) {
        return commentRepository.findByPostOrderByCommentTime(post);
    }
}
