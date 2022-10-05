package ds.pirate.backend.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
// import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import ds.pirate.backend.dto.QuestionDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.dto.reportDTO;
// import ds.pirate.backend.repository.ArticleRepository.getMyChannelArticleList;
import ds.pirate.backend.service.SettingService.SettingService;
// import ds.pirate.backend.vo.channelArticleList;
import ds.pirate.backend.vo.userid;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SettingController {

  private final SettingService service;
  private final String secretKey = "airreviewsecretkeyyyyyyyyyyyyyyyyyyyyyyyy";

  @RequestMapping(value = "/setting/getuser", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<airUserDTO> getByUserId(@RequestBody userid userid) {
    airUserDTO result = service.getByUserId(userid.getUserid());
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "/setting/changepasswd", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> changePasswd(@RequestBody userid vo) {
    log.info(vo);
    return new ResponseEntity<>(service.changePasswd(vo), HttpStatus.OK);
  }

  // @RequestMapping(value = "/setting/articlelist", method = RequestMethod.POST,
  // consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  // public ResponseEntity<HashMap<String, Object>> register(@RequestBody String
  // userid) {
  // log.info(userid);
  // HashMap<String, Object> result =
  // service.settingArticleList(Long.parseLong(userid));
  // return new ResponseEntity<>(result, HttpStatus.OK);
  // }

  @RequestMapping(value = "/setting/articleremove", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> remove2(@RequestBody String aid) {
    return new ResponseEntity<>(service.remove(Long.parseLong(aid)), HttpStatus.OK);
  }

  @RequestMapping(value = "/setting/reportlist", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<reportDTO>> getByList(@RequestBody String user) {
    List<reportDTO> result = service.getReportList(Long.parseLong(user));
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "/setting/reportremove", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> remove(@RequestBody String reid) {
    return new ResponseEntity<>(service.remove(Long.parseLong(reid)), HttpStatus.OK);
  }

  @RequestMapping(value = "/setting/questionlist", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<QuestionDTO>> getByQuestionList(@RequestBody String userid) {
    List<QuestionDTO> result = service.getQuestionList(Long.parseLong(userid));
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @SuppressWarnings("rawtypes")
  @RequestMapping(value = "/setting/image/upload/{userid}", method = RequestMethod.POST)
  public ResponseEntity<String> uploadImage(@PathVariable("userid") Long userid,  @RequestHeader("token") byte[] header, MultipartRequest request) throws Exception {

      String token = new String(header, Charset.forName("utf8"));
      DefaultJws defaultjJws = (DefaultJws) Jwts.parser().setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token.substring(7));
      DefaultClaims claims = (DefaultClaims) defaultjJws.getBody();
      String uid = claims.getId();
      

      if(Integer.parseInt(uid) == userid){
          MultipartFile imageFile = request.getFile("upload");
          String uploadDir = "/Users/hyunseokbyun/Documents/Imagefiles/";
          String uploadId = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(imageFile.getOriginalFilename());
          imageFile.transferTo(new File(uploadDir + uploadId));
          log.info(userid+"getouttttttttttt");
          log.info(uploadDir + uploadId);
          service.uploadProfileImg(userid, uploadId);
          return new ResponseEntity<>(uploadId, HttpStatus.OK);
      }else{
          return new ResponseEntity<>("잘못된접근", HttpStatus.BAD_REQUEST);
      }
  }

}
