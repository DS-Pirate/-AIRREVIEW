package ds.pirate.backend.controller;

import java.util.HashMap;
import java.util.List;
import ds.pirate.backend.vo.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.acommentDTO;
import ds.pirate.backend.service.ArticleService.ArticleService;
import lombok.RequiredArgsConstructor;



@RestController
@Log4j2
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


    @RequestMapping(value = "/comment/", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> commentRead2(@RequestBody comment comment){
        Pageable pageable = PageRequest.of(comment.getReqPage(), 5);
        HashMap<String, Object> result = aservice.getCommentListByAid2(comment.getAid(), pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/articlerecommend/", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> recommend(@RequestBody comment comment){
        Pageable pageable = PageRequest.of(comment.getReqPage(), 5);
        HashMap<String, Object> result = aservice.getCardInfosByHashTagName(comment.getAid(), pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping(value = "/card", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Object[]>> ArticleCardsList(){
//        List<Object[]> result = aservice.getArticleList();
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @GetMapping(value = "/card", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmbedCard>> ArticleCardsList(){
        return new ResponseEntity<>(aservice.getArticleList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmbedCard>> ArticleCardsSearch(@RequestBody search vo){
        log.info(vo);
        return new ResponseEntity<>(aservice.getSearchList(vo), HttpStatus.OK);
    }

    @RequestMapping(value = "/userdetail", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<acommentDTO>> commentUserDetail(@RequestBody commentDetail vo){
        
        return new ResponseEntity<>(aservice.getListByUserIdAndAuthorId(vo.getUserid(), vo.getAuthorid()), HttpStatus.OK);
    }

}
