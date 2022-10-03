package ds.pirate.backend.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import ds.pirate.backend.repository.ArticleRepository.getMyChannelArticleList;
import ds.pirate.backend.repository.UserRepository.UserIntroPage;
import ds.pirate.backend.service.ChannelService.ChannelService;
import ds.pirate.backend.service.UserService.UserService;
import ds.pirate.backend.vo.channelArticleList;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ChannelController {

    private final ChannelService cser;
    private final String secretKey = "airreviewsecretkeyyyyyyyyyyyyyyyyyyyyyyyy";
    private final UserService user;

    @RequestMapping(value = "/mypage/userinfo", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserIntroPage> userintroduce(@RequestBody Long userid) {
        return new ResponseEntity<>(user.getUserInfoByUserId(userid), HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/article", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<getMyChannelArticleList>> commentUserDetail(@RequestBody channelArticleList info) {
        return new ResponseEntity<>(cser.articleListByUserid(info.getUserid(), info.getPageNum(),info.getAtitle()), HttpStatus.OK);
    }
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/mypage/image/upload/{userid}", method = RequestMethod.POST)
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
            log.info(userid);
            log.info(uploadDir + uploadId);
            cser.uploadCHImage(userid, uploadId);
            return new ResponseEntity<>(uploadId, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("잘못된접근", HttpStatus.BAD_REQUEST);
        }
    }

    
}
