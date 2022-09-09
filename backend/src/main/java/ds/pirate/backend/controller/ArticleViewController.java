package ds.pirate.backend.controller;

import java.util.HashMap;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.service.ArticleService.ArticleService;
import ds.pirate.backend.vo.comment;
import ds.pirate.backend.vo.subcard;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/article/")
@RequiredArgsConstructor
public class ArticleViewController {
    

    private final ArticleService aservice;
    @RequestMapping(value = "/read/{aid}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> articleRead(@ModelAttribute("aid") Long aid){
        HashMap<String,Object> result = new HashMap<>();
        ArticleDTO articleInfo = aservice.getArticleInfoByAid(aid);
        Double AVGRate = aservice.getArticleAvgRating(aid);
        result.put("articleInfo", articleInfo);
        result.put("articleAVG", AVGRate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/read/subcard", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> articleRead(@RequestBody subcard vo){
        return new ResponseEntity<>(aservice.getSubscardInfo(vo.getAid(), vo.getUserid()), HttpStatus.OK);
    }

    

    @RequestMapping(value = "/comment/{aid}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> commentRead(@ModelAttribute("aid") Long aid){
        HashMap<String, Object> result = new HashMap<>();
        Object commentList = aservice.getCommentListByAid(aid);
        result.put("commentList", commentList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/comment/", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> commentRead2(@RequestBody comment comment){
        Pageable pageable = PageRequest.of(comment.getReqPage(), 5);
        HashMap<String, Object> result = new HashMap<>();
        Object commentList = aservice.getCommentListByAid2(comment.getAid(), pageable);
        result.put("commentList", commentList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
