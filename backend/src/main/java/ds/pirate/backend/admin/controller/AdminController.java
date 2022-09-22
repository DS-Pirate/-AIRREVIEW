package ds.pirate.backend.admin.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ds.pirate.backend.admin.dto.PageRequestDTO;
import ds.pirate.backend.admin.dto.PageResultDTO;
import ds.pirate.backend.admin.service.AdminService;
import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.QuestionDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.dto.reportDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.QuestionsList;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.reportList;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/api/admin/")
@RestController
@RequiredArgsConstructor
@Log4j2
public class AdminController {

    private final AdminService adser;

    @RequestMapping(value = "/questionanswer", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> insertAnswer(@RequestBody QuestionDTO dto, @RequestHeader(value="userid") Long userid) {
        if(permissionCheckingEverytime(userid)){
            
            return new ResponseEntity<>(adser.qaAnswer(dto), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }  
    }

    @RequestMapping(value = "/reportmanagement/delete", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> reportdelete(@RequestBody reportDTO dto, @RequestHeader(value="userid") Long userid) {
        if(permissionCheckingEverytime(userid)){
            return new ResponseEntity<>(adser.reportCancel(dto.getReid()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }  
    }

    @RequestMapping(value = "/articlemanagement/delete", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> articleDelete(@RequestBody ArticleDTO dto, @RequestHeader(value="userid") Long userid) {
        if(permissionCheckingEverytime(userid)){
            return new ResponseEntity<>(adser.articleDelete(dto), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }  
    }


    @RequestMapping(value = "/articlemanagement/hashs", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> gethashs(@RequestBody ArticleDTO dto, @RequestHeader(value="userid") Long userid) {
        if(permissionCheckingEverytime(userid)){
            return new ResponseEntity<>(adser.getHashs(dto.getAid()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }  
    }

    @RequestMapping(value = "/usermanagement", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDTO<airUserDTO, airUser>> usermanage(@RequestBody PageRequestDTO dto, @RequestHeader(value="userid") Long userid) {
        if(permissionCheckingEverytime(userid)){
            PageResultDTO<airUserDTO, airUser>result = adser.getUserList(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }  
    }


    @RequestMapping(value = "/usermanagement/modify", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> usermodify(@RequestBody airUserDTO dto, @RequestHeader(value="userid") Long userid) {
        if(permissionCheckingEverytime(userid)){
            return new ResponseEntity<>(adser.UserModifier(dto), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }  
    }
    @RequestMapping(value = "/usermanagement/delete", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> userdelete(@RequestBody airUserDTO dto, @RequestHeader(value="userid") Long userid) {
        if(permissionCheckingEverytime(userid)){
            return new ResponseEntity<>(adser.UserDeleter(dto), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }  
    }


    @RequestMapping(value = "/articlemanagement", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDTO<ArticleDTO, ArticlesList>> articlemanage(@RequestBody PageRequestDTO dto, @RequestHeader(value="userid") Long userid) {
        if(permissionCheckingEverytime(userid)){
            PageResultDTO<ArticleDTO, ArticlesList>result = adser.getArticleList(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
            
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }  
    }

    @RequestMapping(value = "/reportmanagement", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDTO<reportDTO, reportList>> reportmanage(@RequestBody PageRequestDTO dto, @RequestHeader(value="userid") Long userid) {
        if(permissionCheckingEverytime(userid)){
            PageResultDTO<reportDTO, reportList>result = adser.getReportList(dto);
            log.info(result);
            return new ResponseEntity<>(result, HttpStatus.OK);
            
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }  
    }

    @RequestMapping(value = "/questionmanagement", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDTO<QuestionDTO, QuestionsList>> questionmanage(@RequestBody PageRequestDTO dto, @RequestHeader(value="userid") Long userid) {
        if(permissionCheckingEverytime(userid)){
            PageResultDTO<QuestionDTO, QuestionsList> result = adser.getQuestionList(dto);
            log.info(result);
            return new ResponseEntity<>(result, HttpStatus.OK);
            
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }  
    }



    public Boolean permissionCheckingEverytime(Long userid) {
        if(!adser.adminChecker(userid)){
            return false;
        } else{
            return true;
        }
    }
}
