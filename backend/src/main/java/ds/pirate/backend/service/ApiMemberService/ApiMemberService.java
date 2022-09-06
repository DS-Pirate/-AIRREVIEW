package ds.pirate.backend.service.ApiMemberService;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.entity.airUser;

import java.util.stream.Collectors;

public interface ApiMemberService {

    String register(airUserDTO dto);

//    roleSet에러ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ
//    default airUserDTO entityToDTO(airUser entity) {
//        airUserDTO dto = airUser.builder()
//                .passwd(entity.getPasswd())
//                .eMail(entity.getEMail())
//                .airName(entity.getAirName())
//                .birthDay(entity.getBirthDay())
//                .gender(entity.getGender())
//                .auth(entity.isAuth())
//                .recentArticles(entity.getRecentArticles())
//                .q1(entity.getQ1())
//                .q2(entity.getQ2())
//                .q3(entity.getQ3())
//                .userIntro(entity.getUserIntro())
//                .roleSet(entity.getRoleSet().stream().map(
//                                role -> new String("ROLE_" + role.name()))
//                        .collect(Collectors.toSet()))
//                .userImg(entity.getUserImg())
//                .chName(entity.getChName())
//                .commentList(entity.getCommentList())
//                .build();
//
//        return dto;
//    }

    default airUser dtoToEntity(airUserDTO dto){
        airUser entity = airUser.builder()
                .passwd(dto.getPasswd())
                .eMail(dto.getEMail())
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



