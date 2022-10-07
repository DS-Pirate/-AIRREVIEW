package ds.pirate.backend.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.service.SavedService.SavedService;
import ds.pirate.backend.vo.userid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class SavedController {

  private final SavedService service;

  @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HashMap<String, Object>> register(@RequestBody userid userid) {
    log.info(userid.getUserid());
    HashMap<String, Object> result = service.getListAid(userid.getUserid());
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
