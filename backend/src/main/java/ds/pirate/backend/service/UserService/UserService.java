package ds.pirate.backend.service.UserService;

import java.util.HashMap;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.repository.UserRepository.UserIntroPage;

public interface UserService {
    

    HashMap<String, String> getUserInfoByuseridForarticle(Long userid);
    UserIntroPage getUserInfoByUserId(Long userid);

    default airUser dtoToEntity(airUserDTO dto){
        airUser entity = airUser.builder()
        .userid(dto.getUserid())
        .airName(dto.getAirName())
        .eMail(dto.getEmail())
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
        .userid(entity.getUserid())
        .airName(entity.getAirName())
        .email(entity.getEMail())
        .birthDay(entity.getBirthDay())
        .gender(entity.getGender())
        .auth(entity.isAuth())
        .recentArticles(entity.getRecentArticles())
        .q1(entity.getQ1())
        .q2(entity.getQ3())
        .q3(entity.getQ2())
        .userIntro(entity.getUserIntro())
        .build();
        return dto;
    }
}
