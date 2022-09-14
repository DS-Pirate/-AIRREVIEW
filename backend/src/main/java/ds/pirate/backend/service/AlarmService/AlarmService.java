package ds.pirate.backend.service.AlarmService;

import java.util.List;

import ds.pirate.backend.dto.alarmDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.acomments;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.alarm;

public interface AlarmService {
    List<Object> getAlarmListByUserid(Long userid);
    void addAlarm(acomments dto);
    void checked(Long arid);
    
    default alarmDTO entityToDTO(alarm entity){
        alarmDTO dto = alarmDTO
        .builder()
        .arid(entity.getArid())
        .articleId(entity.getArticleId().getAid())
        .commentId(entity.getCommentId().getCid())
        .whoUser(entity.getWhoUser().getUserid())
        .toUser(entity.getToUser().getUserid())
        .checked(entity.isChecked())
        .build();
        return dto;
    }

    default alarm dtoToEntity(alarmDTO dto){
        alarm entity = alarm
        .builder()
        .arid(dto.getArid())
        .articleId(ArticlesList.builder().aid(dto.getArticleId()).build())
        .whoUser(airUser.builder().userid(dto.getWhoUser()).build())
        .toUser(airUser.builder().userid(dto.getToUser()).build())
        .commentId(acomments.builder().cid(dto.getCommentId()).build())
        .checked(dto.isChecked())
        .build();
        return entity;
    }
}
