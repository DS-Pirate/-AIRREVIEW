package ds.pirate.backend.controller;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.service.ApiMemberService.ApiMemberService;
import ds.pirate.backend.vo.findpass;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
        return new ResponseEntity<>(email, HttpStatus.OK);
    }

    @RequestMapping(value = "/findpass", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findpass(@RequestBody findpass vo) {
        log.info(vo);

        return null;
    }
}
