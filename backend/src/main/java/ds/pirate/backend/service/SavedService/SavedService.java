package ds.pirate.backend.service.SavedService;

import java.util.HashMap;

import ds.pirate.backend.dto.SaveDTO;
import ds.pirate.backend.entity.SaveList;

public interface SavedService {
  HashMap<String, Object> getListAid(Long aid);

  default SaveList dtoToEntity(SaveDTO dto) {
    SaveList entity = SaveList.builder()
        .aid(dto.getAid())
        .savid(dto.getSavid())
        .userid(dto.getUserid())
        .build();
    return entity;
  }

  default SaveDTO EntityToDto(SaveList entity) {
    SaveDTO dto = SaveDTO.builder()
        .aid(entity.getAid())
        .savid(entity.getSavid())
        .userid(entity.getUserid())
        .build();
    return dto;
  }
}
