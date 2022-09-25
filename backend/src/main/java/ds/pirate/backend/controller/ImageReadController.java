package ds.pirate.backend.controller;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.service.ChannelService.ChannelService;
import ds.pirate.backend.service.UserService.UserImage.UserImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/images/read/")
@RequiredArgsConstructor
public class ImageReadController {
    private final UserImageService uservice;
    private final ChannelService cser;

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> getFile(@ModelAttribute("fileName") String fileName, String size) {
        ResponseEntity<byte[]> result = null;
        try {
            String srchFileName = URLDecoder.decode(fileName, "UTF-8");
            // File file = new File("c:\\testingimage" + File.separator + srchFileName);// window일경우 사용
            File file = new File("/Users/hyunseokbyun/Documents/Imagefiles/" +
            File.separator + srchFileName);
            if (size != null && size.equals("1")) {
                file = new File(file.getParent(), file.getName().substring(2));
            }

            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", Files.probeContentType(file.toPath())); // MIME
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @GetMapping("/ch/{userid}")
    public ResponseEntity<byte[]> getCHImage(@ModelAttribute("userid") Long userid, String size) {
        ResponseEntity<byte[]> result = null;
        try {
            String srchFileName = cser.getCHImage(userid);
            // File file = new File("c:\\testingimage" + File.separator + srchFileName);// window일경우 사용
            File file = new File("/Users/hyunseokbyun/Documents/Imagefiles/" +
            File.separator + srchFileName);
            if (size != null && size.equals("1")) {
                file = new File(file.getParent(), file.getName().substring(2));
            }

            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", Files.probeContentType(file.toPath())); // MIME
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @GetMapping("/userid/{userid}")
    public ResponseEntity<byte[]> getFileByUserId(@ModelAttribute("userid") Long userid, String size) {
        String userFileName = uservice.getUImageNameByUserid(userid);
        ResponseEntity<byte[]> result = null;
        try {
            String srchFileName = URLDecoder.decode(userFileName, "UTF-8");
            // File file = new
            // File("c:\\testingimage"+File.separator+srchFileName);//window일경우 사용
            File file = new File("/Users/hyunseokbyun/Documents/Imagefiles/" + File.separator + srchFileName);
            if (size != null && size.equals("1")) {
                file = new File(file.getParent(), file.getName().substring(2));
            }

            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", Files.probeContentType(file.toPath())); // MIME
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

}
