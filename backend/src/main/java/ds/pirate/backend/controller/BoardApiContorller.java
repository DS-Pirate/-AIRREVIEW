package ds.pirate.backend.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
import javassist.NotFoundException;
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

    @RequestMapping(value = "/write-image", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> imageupload(@RequestBody Object imageInfo) {
        log.info(imageInfo);

        return null;
    }

    @ResponseBody
    @PostMapping("/write/image")
    public Map<String, Object> uploadImage(@RequestParam Map<String, Object> paramMap, MultipartRequest request)
            throws Exception {
        MultipartFile uploadFile = request.getFile("upload");
        String uploadDir = "c:\\testingimage\\";
        String uploadId = UUID.randomUUID().toString() + "."
                + FilenameUtils.getExtension(uploadFile.getOriginalFilename());
        log.info("cccccccccccccccccccccccccccccccccccccc"+ uploadId);
        uploadFile.transferTo(new File(uploadDir + uploadId));
        paramMap.put("url", "/airreview/images/read/?fileName=" + uploadId);
        log.info(paramMap);
        return paramMap;
    }
}
