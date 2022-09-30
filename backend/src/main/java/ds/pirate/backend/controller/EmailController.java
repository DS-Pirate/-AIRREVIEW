package ds.pirate.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.dto.emailAuthDTO;
import ds.pirate.backend.service.MailService.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
@Log4j2
public class EmailController {
    private final MailService mser;
    @RequestMapping(value = "/emailauth", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> emailRequest(@RequestBody emailAuthDTO dto) {
        log.info(dto);
        return new ResponseEntity<Boolean>(mser.mailCheckingRequest(dto.getEmail()), HttpStatus.OK);
    }

    @RequestMapping(value = "/emailcompare", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> emailCompare(@RequestBody emailAuthDTO dto) {
        log.info(dto);
        return new ResponseEntity<String>(mser.mailCheckingNumCheckRequest(dto.getEmail(), dto.getAuthNum()), HttpStatus.OK);
    }
    



    
}
