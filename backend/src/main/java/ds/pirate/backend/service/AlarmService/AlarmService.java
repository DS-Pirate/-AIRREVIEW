package ds.pirate.backend.service.AlarmService;

import ds.pirate.backend.dto.alarmDTO;
import ds.pirate.backend.entity.alarm;

public interface AlarmService {
    
    default alarmDTO entityToDTO(alarm entity){
        alarmDTO dto = alarmDTO
        .builder()
        .arid(entity.getArid())
        .articleId(entity.getArticleId())
        .whoUser(entity.getWhoUser())
        .toUser(entity.getToUser())
        .commentContext(entity.getCommentContext())
        .checked(entity.isChecked())
        .build();
        return dto;
    }

    default alarm dtoToEntity(alarmDTO dto){
        alarm entity = alarm
        .builder()
        .arid(dto.getArid())
        .articleId(dto.getArticleId())
        .whoUser(dto.getWhoUser())
        .toUser(dto.getToUser())
        .commentContext(dto.getCommentContext())
        .checked(dto.isChecked())
        .build();
        return entity;
    }
}
