package ds.pirate.backend.controller;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.service.ApiMemberService.ApiMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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
}
