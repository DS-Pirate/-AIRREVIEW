package ds.pirate.backend.service.SubscribeService;

import java.util.List;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.dto.subScribeDTO;
import ds.pirate.backend.entity.subscribList;
import ds.pirate.backend.vo.MySubInfo;
import ds.pirate.backend.vo.subcard;

public interface SubscribeService {
  List<airUserDTO> getListUserId(Long userid);

  List<MySubInfo> getFollowingInfoList(subcard vo);
  List<MySubInfo> getFollowerInfoList(subcard vo);
  Long getFollowUnFollowView(subcard vo);
  String FollowUnFollow(subcard vo);

  MySubInfo getFolloCount(subcard vo);

  default subscribList dtoToEntity(subScribeDTO dto) {
    subscribList entity = subscribList.builder()
        .sbid(dto.getSbid())
        .targetId(dto.getTargetId())
        .userid(dto.getUserid())
        .build();
    return entity;
  }

  default subScribeDTO EntityToDto(subscribList entity) {
    subScribeDTO dto = subScribeDTO.builder()
        .sbid(entity.getSbid())
        .targetId(entity.getTargetId())
        .userid(entity.getUserid())
        .build();
    return dto;
  }
}
