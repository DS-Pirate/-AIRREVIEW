package ds.pirate.backend.controller;

import ds.pirate.backend.service.SubscribeService.SubscribeService;
import ds.pirate.backend.vo.MySubInfo;
import ds.pirate.backend.vo.subcard;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mypage/")
@RequiredArgsConstructor
@Log4j2
public class SubController {

  private final SubscribeService service;

  @RequestMapping(value = "/following", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<MySubInfo>> channelFollowing(@RequestBody subcard vo) {
    log.info(vo);
    return new ResponseEntity<>(service.getFollowingInfoList(vo), HttpStatus.OK);
  }

  @RequestMapping(value = "/follower", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<MySubInfo>> channelFollower(@RequestBody subcard vo) {
    log.info(vo);
    return new ResponseEntity<>(service.getFollowerInfoList(vo), HttpStatus.OK);
  }

  @RequestMapping(value = "/followcount", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MySubInfo> FollCount(@RequestBody subcard vo) {
    log.info("이거 숫자");
    log.info(vo);
    return new ResponseEntity<>(service.getFolloCount(vo), HttpStatus.OK);
  }
}