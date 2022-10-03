package ds.pirate.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.dto.rankingDTO;
import ds.pirate.backend.service.RankingService.RankingService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rkser;

    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<rankingDTO>> getList(){
        return new ResponseEntity<>(rkser.gerRanking(), HttpStatus.OK);
    }
}
