package ds.pirate.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ds.pirate.backend.service.EmbedService.EmbedService;
import ds.pirate.backend.vo.Embed;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmbedController {
    private final EmbedService eser;

    @RequestMapping(value = "/info/{article}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Embed> commentUserDetail(@ModelAttribute("article") Long article) {
        Embed result = eser.getEmbedInfo(article);
        Integer substr1 = result.getContext().indexOf("<figu");
        Integer substr2 = result.getContext().lastIndexOf("</figure>");
        if (substr1 != -1) {
            result.setContext(
                    result.getContext().substring(0, substr1) + "[생략된 이미지]" + result.getContext().substring(substr2));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
