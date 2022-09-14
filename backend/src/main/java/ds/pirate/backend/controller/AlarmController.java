package ds.pirate.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.service.AlarmService.AlarmService;
import ds.pirate.backend.vo.userid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;



@RequestMapping("/api/alarm")
@RestController
@RequiredArgsConstructor
@Log4j2
public class AlarmController {
    private final AlarmService alser;
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Object>> removeComment(@RequestBody userid vo){        
        return new ResponseEntity<>(alser.getAlarmListByUserid(vo.getUserid()), HttpStatus.OK);
    }

    @RequestMapping(value = "/checked", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> alarmChecked(@RequestBody Long arid){    
        alser.checked(arid);    
        log.info(arid+"얘들어옴");
        return null;
    }


}
