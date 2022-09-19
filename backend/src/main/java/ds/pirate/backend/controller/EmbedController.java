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
    public ResponseEntity<Embed> commentUserDetail(@ModelAttribute("article") Long article){
        return new ResponseEntity<>(eser.getEmbedInfo(article), HttpStatus.OK);
    }


}
