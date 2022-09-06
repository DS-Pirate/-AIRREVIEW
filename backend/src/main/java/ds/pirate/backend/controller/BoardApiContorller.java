package ds.pirate.backend.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.SaveDTO;
import ds.pirate.backend.dto.acommentDTO;
import ds.pirate.backend.dto.acommentRateDTO;
import ds.pirate.backend.dto.likeUnlikeDTO;
import ds.pirate.backend.dto.reportDTO;
import ds.pirate.backend.service.ArticleService.ArticleService;
import ds.pirate.backend.vo.functioncheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
@Log4j2
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!매우 중요 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//나중에 token도 같이 받아서 dto랑 토큰 같이넘겨서 .set으로 토큰값 덮어씌우기
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!매우 중요 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class BoardApiContorller {

    private final ArticleService aser;

    @RequestMapping(value = "/write", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody ArticleDTO dto) {
        List<String> hashlist = dto.getTags();
        return new ResponseEntity<>(aser.addArticle(dto, hashlist), HttpStatus.OK);
    }


    @ResponseBody
    @PostMapping("/write/image")
    public Map<String, Object> uploadImage(@RequestParam Map<String, Object> paramMap, MultipartRequest request)
            throws Exception {
        MultipartFile uploadFile = request.getFile("upload");
        // String uploadDir = "c:\\testingimage\\"; //windows 일경우 이 경로 사용
        String uploadDir = "/Users/hyunseokbyun/Documents/Imagefiles/";
        String uploadId = UUID.randomUUID().toString() + "."
                + FilenameUtils.getExtension(uploadFile.getOriginalFilename());
        uploadFile.transferTo(new File(uploadDir + uploadId));
        paramMap.put("url", "/airreview/images/read/"+uploadId);
        
        return paramMap;
    }

    @RequestMapping(value = "/comment/{aid}/{userid}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> commentReadWithUserid(@ModelAttribute("aid") Long aid, @ModelAttribute("userid") Long userid){
        // HashMap<String, Object> result = new HashMap<>();
        // Object commentList = aser.getCommentListByAid(aid);
        // result.put("commentList", commentList);
        
        HashMap<String, Object> result = aser.getCommentListByAidTwo(aid, userid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/add/", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addComment(@RequestBody acommentDTO dto){
        Long result = aser.addNewComment(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/add/reply", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addCommentReply(@RequestBody acommentDTO dto){
        Long result = aser.addNewCommentReply(dto);
        log.info(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/add/rating/", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> ratingCommentPlus(@RequestBody acommentRateDTO dto){        
        return new ResponseEntity<>(aser.rateupComment(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> reportingArticle(@RequestBody reportDTO dto){        
        return new ResponseEntity<>(aser.addArticleReport(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/like", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> LikeArticle(@RequestBody likeUnlikeDTO dto){        
        return new ResponseEntity<>(aser.addLikeUnlike(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveArticle(@RequestBody SaveDTO dto){        
        return new ResponseEntity<>(aser.addSave(dto), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/comment/remove", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeComment(@RequestBody acommentDTO dto){        
        return new ResponseEntity<>(aser.removeComment(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/functions", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Boolean>> functionsChecking(@RequestBody functioncheck vo){        
        return new ResponseEntity<>(aser.getFunctionBtnStatusByUserid(vo.getUserid(), vo.getAid()) , HttpStatus.OK);
    }


}
