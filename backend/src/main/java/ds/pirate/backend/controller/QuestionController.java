package ds.pirate.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.dto.QuestionDTO;
import ds.pirate.backend.service.QuestionService.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor

public class QuestionController {

  private final QuestionService qService;

  @RequestMapping(value = "/api/question", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> reportingArticle(@RequestBody QuestionDTO dto) {     
      return new ResponseEntity<>(qService.sendQuestion(dto) , HttpStatus.OK);
  }

}
