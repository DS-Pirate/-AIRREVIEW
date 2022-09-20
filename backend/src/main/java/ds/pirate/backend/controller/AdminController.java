package ds.pirate.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ds.pirate.backend.vo.userid;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/admin/")
@RestController
@RequiredArgsConstructor
public class AdminController {
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Object>> removeComment(@RequestBody userid vo) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // @RequestMapping(value = "/manage", method = RequestMethod.POST, consumes =
    // MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<List<Object>> removeComment(@RequestBody userid vo){
    // return new ResponseEntity<>(alser.getAlarmListByUserid(vo.getUserid()),
    // HttpStatus.OK);
    // }
}
