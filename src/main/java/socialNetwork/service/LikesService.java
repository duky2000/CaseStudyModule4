package socialNetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialNetwork.model.Likes;
import socialNetwork.model.Post;
import socialNetwork.model.User;
import socialNetwork.repository.ILikesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LikesService implements ILikesService {
    @Autowired
    ILikesRepository likesRepository;
    @Override
    public Iterable<Likes> findAll() {
        return likesRepository.findAll();
    }

    @Override
    public Optional<Likes> findById(Long id) {
        return likesRepository.findById(id);
    }

    @Override
    public Likes save(Likes likes) {
        return likesRepository.save(likes);
    }

    @Override
    public void deleteById(Long id) {
        likesRepository.deleteById(id);
    }


    @Override
    public Integer findSumLikes(Long postid) {
        return likesRepository.findSumLikes(postid);
    }

    @Override
    public Integer findLastestLikes(Long postid, Long userid) {
        return likesRepository.findLastestLikes(postid, userid);
    }

    @Override
    public Likes findByPostAndUser(Post post, User user) {
        return likesRepository.findByPostAndUser(post, user);
    }

    @Override
    public List<Likes> findAllByPost(Post post) {
        return likesRepository.findAllByPost(post);
    }

    @Override
    public List<Likes> findLikeNotify(Long userid) {
        return likesRepository.findLikeNotify(userid);
    }

    @Override
    public List<Likes> findAllLikedPeopleByPost(Long postid) {
        return likesRepository.findAllLikedPeopleByPost(postid);
    }
}
