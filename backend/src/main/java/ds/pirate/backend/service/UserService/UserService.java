package ds.pirate.backend.service.UserService;

import java.util.HashMap;

import ds.pirate.backend.dto.UserApiDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.entity.airUser;

public interface UserService {
    
    Long register(UserApiDTO dto);
    airUserDTO getUserInfoByuserid(Long userid);

    HashMap<String, String> getUserInfoByuseridForarticle(Long userid);

    default airUser dtoToEntity(airUserDTO dto){
        airUser entity = airUser.builder()
        .airName(dto.getAirName())
        .eMail(dto.getEMail())
        .birthDay(dto.getBirthDay())
        .gender(dto.getGender())
        .auth(dto.isAuth())
        .recentArticles(dto.getRecentArticles())
        .q1(dto.getQ1())
        .q2(dto.getQ3())
        .q3(dto.getQ2())
        .userIntro(dto.getUserIntro())
        .build();
        return entity;
    }

    default airUserDTO entityToDTO(airUser entity){
        airUserDTO dto = airUserDTO.builder()
        .airName(entity.getAirName())
        .eMail(entity.getEMail())
        .birthDay(entity.getBirthDay())
        .gender(entity.getGender())
        .auth(entity.isAuth())
        .recentArticles(entity.getRecentArticles())
        .q1(entity.getQ1())
        .q2(entity.getQ3())
        .q3(entity.getQ2())
        .userIntro(entity.getUserIntro())
        .build();
        //UImageList와 acomments는 service에서 hashmap으로 합치거나 따로 불러서 넘겨주는 것을 추천 
        return dto;
    }
}
