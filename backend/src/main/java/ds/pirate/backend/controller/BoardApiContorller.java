package ds.pirate.backend.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiContorller {

    private final ArticleRepository arepo;

    @RequestMapping(value = "/write", method = RequestMethod.POST, 
    consumes = MediaType.ALL_VALUE, 
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody ArticleDTO dto, @RequestHeader("token") String token){
        log.info("write dto is cominggggggggggggggggggggg"+ dto);

        return null;
    }
    
}
