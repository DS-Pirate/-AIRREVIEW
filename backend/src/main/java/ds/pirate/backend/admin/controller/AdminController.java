package ds.pirate.backend.admin.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// import ds.pirate.backend.admin.service.AdminService;
import ds.pirate.backend.security.util.JWTUtil;
import ds.pirate.backend.vo.userid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/admin/")
@RestController
@RequiredArgsConstructor
public class AdminController {

    // private final AdminService adser;
    private final JWTUtil jwtUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeComment(@RequestBody userid vo, @RequestHeader(value="userid") String userid, @RequestHeader(value="Authorization") String tokenStr) {
        
        try {
            permissionCheckingEverytime(userid, tokenStr);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }



        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // @RequestMapping(value = "/manage", method = RequestMethod.POST, consumes =
    // MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<List<Object>> removeComment(@RequestBody userid vo){
    // return new ResponseEntity<>(alser.getAlarmListByUserid(vo.getUserid()),
    // HttpStatus.OK);
    // }

    public void permissionCheckingEverytime(String userid, String tokenStr) {
        jwtUtil.validateAdmin(userid, tokenStr);
    }
}
