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



@RequestMapping("/api/alarm")
@RestController
@RequiredArgsConstructor
public class AlarmController {
    private final AlarmService alser;
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Object>> removeComment(@RequestBody userid vo){        
        return new ResponseEntity<>(alser.getAlarmListByUserid(vo.getUserid()), HttpStatus.OK);
    }

    @RequestMapping(value = "/checked", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> alarmChecked(@RequestBody Long arid){    
        alser.checked(arid);    
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> alarmDelete(@RequestBody Long arid){    
        alser.deleteAlarm(arid);
        return null;
    }


}
