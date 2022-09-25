package ds.pirate.backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ds.pirate.backend.repository.ArticleRepository.getMyChannelArticleList;
import ds.pirate.backend.service.ChannelService.ChannelService;
import ds.pirate.backend.vo.channelArticleList;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService cser;

    @RequestMapping(value = "/mypage/article", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<getMyChannelArticleList>> commentUserDetail(@RequestBody channelArticleList info){
        return new ResponseEntity<>(cser.articleListByUserid(info.getUserid(), info.getPageNum()), HttpStatus.OK);
    }
}
