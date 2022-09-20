package ds.pirate.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.service.SubscribeService.SubscribeService;
import ds.pirate.backend.vo.userid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class SubscribeController {

  private final SubscribeService service;

  @RequestMapping(value = "/subs", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<airUserDTO>> register(@RequestBody userid userid) {
    List<airUserDTO> result = service.getListUserId(userid.getUserid());
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
