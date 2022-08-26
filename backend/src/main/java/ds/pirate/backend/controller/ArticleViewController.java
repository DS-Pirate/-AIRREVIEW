package ds.pirate.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.service.ArticleService.ArticleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/article/")
@RequiredArgsConstructor
public class ArticleViewController {
    

    private final ArticleService aservice;

    @RequestMapping(value = "/read/{aid}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDTO> articleRead(@ModelAttribute("aid") long aid){

        ArticleDTO result = aservice.getArticleInfoByAid(aid);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
