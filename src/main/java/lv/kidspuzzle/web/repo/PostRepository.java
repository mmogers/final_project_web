package lv.kidspuzzle.web.repo;

import lv.kidspuzzle.web.models.Post;
import org.springframework.data.repository.CrudRepository;

//all basic manipulations crud
public interface PostRepository extends CrudRepository <Post, Long>{ //Long for unique id

}
