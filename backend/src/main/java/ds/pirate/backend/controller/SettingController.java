package ds.pirate.backend.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.repository.ArticleReportRepository.getMySettingReportList;
import ds.pirate.backend.repository.ArticleRepository.getMySettingArticleList;
import ds.pirate.backend.service.SettingService.SettingService;
import ds.pirate.backend.vo.settingArticleList;
import ds.pirate.backend.vo.settingReportList;
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
  // public ResponseEntity<HashMap<String, Object>>
  // settingArticleList(@RequestBody userid userid) {
  // log.info(userid);
  // HashMap<String, Object> result =
  // service.settingArticleList(userid.getUserid());
  // return new ResponseEntity<>(result, HttpStatus.OK);
  // }

  @RequestMapping(value = "/setting/articlelist", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<getMySettingArticleList>> commentUserDetail(@RequestBody settingArticleList info) {
    log.info(info);
    return new ResponseEntity<>(service.articleListByUserid(info.getUserid(), info.getPageNum()), HttpStatus.OK);
  }

  @RequestMapping(value = "/setting/reportlist", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<getMySettingReportList>> commentUserDetail2(@RequestBody settingReportList info) {
    log.info(info);
    return new ResponseEntity<>(service.reportListByUserid(info.getUserid(), info.getPageNum()), HttpStatus.OK);
  }
}
