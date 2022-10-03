package ds.pirate.backend.controller;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.service.ApiMemberService.ApiMemberService;
import ds.pirate.backend.vo.findpass;
import ds.pirate.backend.vo.setpass;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class ApiMemberController {

    private final ApiMemberService service;

    @RequestMapping(value = "/register", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody airUserDTO dto) {
        String email = service.register(dto);
        if (email =="회원가입에 실패하였습니다.") {
            return new ResponseEntity<>(email, HttpStatus.BAD_REQUEST);    
        }
        return new ResponseEntity<>(email, HttpStatus.OK);
    }

    @RequestMapping(value = "/findpass", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> findpass(@RequestBody findpass vo) {
        log.info(vo);
        log.info(service.findPass(vo));
            return new ResponseEntity<>(service.findPass(vo),HttpStatus.OK);
    }

    @RequestMapping(value = "/setpass", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setpass(@RequestBody setpass vo) {
        log.info(vo);
        return new ResponseEntity<>(service.changePass(vo),HttpStatus.OK);
    }

    @RequestMapping(value = "/findemail", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findemail(@RequestBody airUserDTO email) {
        log.info(email);
        return new ResponseEntity<>(service.findEmail(email),HttpStatus.OK);
    }
}
