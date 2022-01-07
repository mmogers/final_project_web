package lv.kidspuzzle.web.controlles;

import lv.kidspuzzle.web.models.Post;
import lv.kidspuzzle.web.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {
    //all pages for blog- add, edit, delete

    //add all posts from table Posts
    @Autowired //for creating a variable that links to repository
    private PostRepository postRepository;




    @GetMapping("/blog")
    public String blogMain(Model model){
        //we will transfer to view the name of the topic and text of the topic
        Iterable <Post> posts = postRepository.findAll(); //array of data from db,
        model.addAttribute("posts", posts); //name - posts, array posts are transferred to view blog-main
        return "blog-main";
    }

    //when we redirected to this page
    @GetMapping("/blog/add")
    public String blogAdd(Model model){

        return "blog-add";
    }

    //when we pushed the button add a post
    //receive data from post table using post
    @PostMapping("/blog/add")
    public String addPostToBlog(@RequestParam String author, @RequestParam String full_text, Model model){
        //need to receive all the data from the post input table
        Post post = new Post(full_text, author);
        postRepository.save(post);//save object post to bd
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}") //dynamic parameter
    public String blogDetails(@PathVariable(value="id") long id, Model model){

        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }

        Optional<Post> post = postRepository.findById(id);
        //not to transfer optional to view
        ArrayList<Post> result = new ArrayList<>();
        post.ifPresent(result::add);
        model.addAttribute("post", result);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value="id") long id, Model model){ //dynamic parameter through URL

        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }

        Optional<Post> post = postRepository.findById(id);
        //not to transfer optional to view
        ArrayList<Post> result = new ArrayList<>();
        post.ifPresent(result::add);
        model.addAttribute("post", result);
        return "blog-edit";
    }

    //when pushing button update on edit screen
    @PostMapping("/blog/{id}/edit")
    public String addPostUpdate(@PathVariable(value="id") long id, @RequestParam String author, @RequestParam String full_text, Model model){

        //not creating new object not adding new id , but just finding the existing
        Post post = postRepository.findById(id).orElseThrow();//throw if was not found
        post.setAuthor(author);
        post.setFull_text(full_text);
        postRepository.save(post);//not the new object but updated existing one

        return "redirect:/blog"; // after editing redirects to blog
    }

    //when pushing on delete button in edit screen
    @PostMapping("/blog/{id}/remove")
    public String addPostDelete(@PathVariable(value="id") long id, Model model){

        //not creating new object not adding new id , but just finding the existing
        Post post = postRepository.findById(id).orElseThrow();//throw if was not found
        postRepository.delete(post);

        return "redirect:/blog"; // after editing redirects to blog
    }

}//end of class
