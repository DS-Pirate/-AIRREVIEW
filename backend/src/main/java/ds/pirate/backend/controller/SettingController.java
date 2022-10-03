package ds.pirate.backend.controller;

import java.util.List;

// import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.dto.QuestionDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.dto.reportDTO;
// import ds.pirate.backend.repository.ArticleRepository.getMyChannelArticleList;
import ds.pirate.backend.service.SettingService.SettingService;
// import ds.pirate.backend.vo.channelArticleList;
import ds.pirate.backend.vo.userid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SettingController {

  private final SettingService service;

  @RequestMapping(value = "/setting/getuser", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<airUserDTO> getByUserId(@RequestBody userid userid) {
    airUserDTO result = service.getByUserId(userid.getUserid());
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "/setting/changepasswd", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> changePasswd(@RequestBody userid vo) {
    log.info(vo);
    return new ResponseEntity<>(service.changePasswd(vo), HttpStatus.OK);
  }

  // @RequestMapping(value = "/setting/articlelist", method = RequestMethod.POST,
  // consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  // public ResponseEntity<HashMap<String, Object>> register(@RequestBody String
  // userid) {
  // log.info(userid);
  // HashMap<String, Object> result =
  // service.settingArticleList(Long.parseLong(userid));
  // return new ResponseEntity<>(result, HttpStatus.OK);
  // }

  @RequestMapping(value = "/setting/articleremove", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> remove2(@RequestBody String aid) {
    return new ResponseEntity<>(service.remove(Long.parseLong(aid)), HttpStatus.OK);
  }

  @RequestMapping(value = "/setting/reportlist", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<reportDTO>> getByList(@RequestBody String user) {
    List<reportDTO> result = service.getReportList(Long.parseLong(user));
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "/setting/reportremove", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> remove(@RequestBody String reid) {
    return new ResponseEntity<>(service.remove(Long.parseLong(reid)), HttpStatus.OK);
  }

  @RequestMapping(value = "/setting/questionlist", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<QuestionDTO>> getByQuestionList(@RequestBody String userid) {
    List<QuestionDTO> result = service.getQuestionList(Long.parseLong(userid));
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  // @RequestMapping(value = "/setting/articlelist", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  // public ResponseEntity<Page<getMyChannelArticleList>> commentUserDetail(@RequestBody channelArticleList info) {
  //   return new ResponseEntity<>(service.articleListByUserid(info.getUserid(), info.getPageNum()), HttpStatus.OK);
  // }

}
