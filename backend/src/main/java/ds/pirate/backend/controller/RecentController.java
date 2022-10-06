package ds.pirate.backend.controller;


import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.service.RecentArticleService.RecentArticleService;
import ds.pirate.backend.vo.subcard;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class RecentController {
    private final RecentArticleService raser;
    
    @RequestMapping(value = "/recent/list", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> getRecentListByUserid(@RequestBody subcard vo){
        HashMap<String, Object> result = raser.getArticlesListDtoByUserId(vo.getUserid(), vo.getReqPage());
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
