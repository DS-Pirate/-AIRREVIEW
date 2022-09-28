package ds.pirate.backend.controller;

import ds.pirate.backend.service.ArticleService.ArticleService;
import ds.pirate.backend.vo.comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;


@Log4j2
@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class ApiController {

    private final ArticleService aservice;

    @RequestMapping(value = "/article/card/sub", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmbedCard>> ArticleCardsOrderSub(@RequestBody comment vo) {
        log.info(vo);
        return new ResponseEntity<>(aservice.getArticleListBySub(vo), HttpStatus.OK);
    }

}
