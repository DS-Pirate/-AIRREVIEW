package ds.pirate.backend.controller;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.acommentDTO;
import ds.pirate.backend.service.ArticleService.ArticleService;
import ds.pirate.backend.service.UserService.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/article/")
@RequiredArgsConstructor
public class ArticleViewController {
    

    private final ArticleService aservice;
    private final UserService uservice;
    @RequestMapping(value = "/read/{aid}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> articleRead(@ModelAttribute("aid") Long aid){
        HashMap<String,Object> result = new HashMap<>();
        ArticleDTO articleInfo = aservice.getArticleInfoByAid(aid);
        Object userInfo = uservice.getUserInfoByuseridForarticle(articleInfo.getUserId());
        result.put("articleInfo", articleInfo);
        result.put("userInfo", userInfo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/{aid}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> commentRead(@ModelAttribute("aid") Long aid){
        HashMap<String, Object> result = new HashMap<>();

        Object commentList = aservice.getCommentListByAid(aid);
        
        result.put("commentList", commentList);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/api/comment/add/", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addComment(@RequestBody acommentDTO dto){
        

        return null;
    }
}
