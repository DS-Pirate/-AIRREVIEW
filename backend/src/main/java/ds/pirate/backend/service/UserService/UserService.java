package ds.pirate.backend.service.UserService;

import java.util.HashMap;

import ds.pirate.backend.dto.UserApiDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.entity.airUser;

public interface UserService {
    
    Long register(UserApiDTO dto);
    airUserDTO getUserInfoByuserid(Long userid);

    HashMap<String, String> getUserInfoByuseridForarticle(Long userid);


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
