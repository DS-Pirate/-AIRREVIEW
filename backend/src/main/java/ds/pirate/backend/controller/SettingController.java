package ds.pirate.backend.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.service.SettingService.SettingService;
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

  @RequestMapping(value = "/setting/changePasswd", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> changePasswd(@RequestBody userid vo) {
    log.info(vo);
    return new ResponseEntity<>(service.changePasswd(vo), HttpStatus.OK);
  }
}
