package ds.pirate.backend.service.SettingService;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.entity.airUser;

public interface SettingService {

  airUserDTO getByUserId(Long userid);

  String changePasswd(airUserDTO dto);

  default airUserDTO entityToDTO(airUser entity) {
    airUserDTO dto = airUserDTO.builder()
        .passwd(entity.getPasswd())
        .email(entity.getEMail())
        .airName(entity.getAirName())
        .birthDay(entity.getBirthDay())
        .gender(entity.getGender())
        .auth(entity.isAuth())
        .recentArticles(entity.getRecentArticles())
        .q1(entity.getQ1())
        .q2(entity.getQ2())
        .q3(entity.getQ3())
        .userIntro(entity.getUserIntro())
        .build();

    return dto;
  }

  default airUser dtoToEntity(airUserDTO dto) {
    airUser entity = airUser.builder()
        .passwd(dto.getPasswd())
        .eMail(dto.getEmail())
        .airName(dto.getAirName())
        .birthDay(dto.getBirthDay())
        .gender(dto.getGender())
        .auth(dto.isAuth())
        .recentArticles(dto.getRecentArticles())
        .q1(dto.getQ1())
        .q2(dto.getQ2())
        .q3(dto.getQ3())
        .userIntro(dto.getUserIntro())
        .chName(dto.getChName())
        .build();

    return entity;
  }
}