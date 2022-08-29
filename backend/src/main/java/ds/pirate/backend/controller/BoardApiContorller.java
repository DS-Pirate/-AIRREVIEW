package ds.pirate.backend.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;



import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import ds.pirate.backend.service.ArticleService.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class BoardApiContorller {

    private final ArticleService aser;

    @RequestMapping(value = "/write", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody ArticleDTO dto) {
        log.info("write dto is cominggggggggggggggggggggg" + dto);
        List<String> hashlist = dto.getTags();
        return new ResponseEntity<>(aser.addArticle(dto, hashlist), HttpStatus.OK);
    }


    @ResponseBody
    @PostMapping("/write/image")
    public Map<String, Object> uploadImage(@RequestParam Map<String, Object> paramMap, MultipartRequest request)
            throws Exception {
        MultipartFile uploadFile = request.getFile("upload");
        String uploadDir = "c:\\testingimage\\"; //windows 일경우 이 경로 사용
        // String uploadDir = "/Users/hyunseokbyun/Documents/Imagefiles/";
        String uploadId = UUID.randomUUID().toString() + "."
                + FilenameUtils.getExtension(uploadFile.getOriginalFilename());
        log.info("cccccccccccccccccccccccccccccccccccccc"+ uploadId);
        uploadFile.transferTo(new File(uploadDir + uploadId));
        paramMap.put("url", "/airreview/images/read/"+uploadId);
        
        log.info(paramMap);
        return paramMap;
    }
}
