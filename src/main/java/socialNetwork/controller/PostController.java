package socialNetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import socialNetwork.model.Comment;
import socialNetwork.model.Likes;
import socialNetwork.model.Post;
import socialNetwork.service.ICommentService;
import socialNetwork.service.ILikesService;
import socialNetwork.service.IPostService;
import socialNetwork.service.user.IUserService;


import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {
    @Value("${img-path}")
    private String fileUpload;
    @Autowired
    IPostService postService;
    @Autowired
    IUserService userService;

    @Autowired
    ICommentService commentService;

    @Autowired
    ILikesService likesService;

    @PostMapping("/create-post")
    public ModelAndView createPost(@RequestParam("site") String site, @RequestParam("imgPost") MultipartFile imgPost, @ModelAttribute Post post) {
        ModelAndView modelAndView;
        if (site.equals("profile")) modelAndView = new ModelAndView("redirect:/profile");
        modelAndView = new ModelAndView("redirect:/");
        try {
            String nameImgPost = imgPost.getOriginalFilename();
            FileCopyUtils.copy(imgPost.getBytes(), new File(fileUpload + "static/uploaded/" + nameImgPost));
            post.setImg("/resource/static/uploaded/" + nameImgPost);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Date date = new Date();
        Timestamp postTime = new Timestamp(date.getTime());
        post.setPostTime(postTime);
        post.setUser(userService.getPrincipal());
        postService.save(post);
        return modelAndView;
    }

    @GetMapping("/edit-post")
    public ModelAndView showEditForm(@RequestParam("id") Long id) {
        return new ModelAndView("post/editpost", "post", postService.findById(id).get());
    }

    @PostMapping("/edit-post")
    public ModelAndView editPost(@RequestParam("imgPost") MultipartFile imgPost, @ModelAttribute Post post) {
        ModelAndView modelAndView = new ModelAndView("redirect:/profile?iduser=" + userService.getPrincipal().getId());
        try {
            String nameImgPost = imgPost.getOriginalFilename();
            FileCopyUtils.copy(imgPost.getBytes(), new File(fileUpload + "static/uploaded/" + nameImgPost));
            post.setImg("/resource/static/uploaded/" + nameImgPost);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        post.setUser(userService.getPrincipal());
        postService.save(post);
        return modelAndView;
    }

    @GetMapping("/delete-post/{id}")
    public ModelAndView deletePost(@PathVariable("id") Long id) {
        List<Likes> likesList = likesService.findAllByPost(postService.findById(id).get());
        if (likesList == null) ;
        else {
            for (Likes l : likesList) {
                likesService.deleteById(l.getId());
            }
        }
        List<Comment> comments = (List<Comment>) commentService.findByPostOrderByCommentTime(postService.findById(id).get());
        if (comments == null) ;
        else {
            for (Comment c : comments) {
                commentService.deleteById(c.getId());
            }
        }
        Long iduser = postService.findById(id).get().getUser().getId();
        postService.deleteById(id);
        return new ModelAndView("redirect:/profile?iduser=" + iduser);
    }

    @GetMapping("/detail-post")
    public ModelAndView showDetailPost(@RequestParam("id") Long id, @ModelAttribute("errorCode") String errorCode) {
        ModelAndView modelAndView = new ModelAndView("post/fbdetailpost");
        Post post = postService.findById(id).get();
        ArrayList<Comment> comments = (ArrayList<Comment>) commentService.findByPostOrderByCommentTime(post);
        modelAndView.addObject("post", post);
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("comment", new Comment());
        if (errorCode == null) ;
        else modelAndView.addObject("errorCode", errorCode);
        Integer sumLikes = 0;
        try {
            sumLikes = likesService.findSumLikes(id);
        } catch (Exception e) {
            sumLikes = 0;
        }
        modelAndView.addObject("sumLikes", sumLikes);
       String result = "";
        Date date = new Date();
        long milliseconds2 = date.getTime();
            long milliseconds1 = post.getPostTime().getTime();
            int result1 = (int) ((milliseconds2 - milliseconds1)/1000);
            int result2 = (int) (result1/60);
            int result3 = (int) (result2/60);
            int result4 = (int) (result3/24);
            if (result1 < 60)  result = result1 + " seconds ago";
            else if (result2 < 60) result = result2 + " minutes ago";
            else if (result3 < 24) result = result3 + " hours ago";
            else result = result4 + " days ago";
        modelAndView.addObject("result", result);
        if (likesService.findAllLikedPeopleByPost(id) != null)
        modelAndView.addObject("allLikedPeople", likesService.findAllLikedPeopleByPost(id));
        return modelAndView;
    }

//    @GetMapping("/detail-post-fb")
//    public String getDetailPostFb() {
//        return "post/fbdetailpost";
//    }

}
