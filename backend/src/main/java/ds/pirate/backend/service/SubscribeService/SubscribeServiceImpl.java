package ds.pirate.backend.service.SubscribeService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.vo.MySubInfo;
import ds.pirate.backend.vo.subcard;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.entity.subscribList;
import ds.pirate.backend.repository.SubscribeRepository;
import ds.pirate.backend.repository.UserRepository;
import ds.pirate.backend.service.UserService.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {
  private final SubscribeRepository subrepo;
  private final UserRepository urepo;
  private final UserService uService;

  @Override
  public List<MySubInfo> getListUserId(Long userid) {
//    List<airUserDTO> result = subrepo.getByUserId(userid).get().stream()
//        .map((Function<? super subscribList, ? extends airUserDTO>) v -> {
//          airUserDTO list = uService.entityToDTO(urepo.findByUserId(v.getTargetId()).get());
//          return list;
//        }).collect(Collectors.toList());
      List<MySubInfo> result = subrepo.getSubInfoAndImgByUserId(userid).get().stream().map(v->{
          return new MySubInfo(v);
      }).collect(Collectors.toList());
    return result;
  }
  
    @Override
    public List<MySubInfo> getFollowingInfoList(subcard vo) {
        Pageable pageable = PageRequest.of(vo.getReqPage(), 9,Sort.by(Sort.Direction.DESC, "follower"));
        List<MySubInfo> result = subrepo.getPostFollwerFollwingInSubByUserid(vo.getUserid(), pageable).get().stream().map(v->{
            return new MySubInfo(v);
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<MySubInfo> getFollowerInfoList(subcard vo) {
        Pageable pageable = PageRequest.of(vo.getReqPage(), 9,Sort.by(Sort.Direction.DESC, "follower"));
        List<MySubInfo> result = subrepo.getPostFollwerFollwingInFollowerByUserid(vo.getUserid(), pageable).get().stream().map(v->{
            return new MySubInfo(v);
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public Long getFollowUnFollowView(subcard vo) {
      Long result = subrepo.getFollowingByUseridAndTargetid(vo.getUserid(), vo.getAid());
        return result;
    }

    @Override
    public String FollowUnFollow(subcard vo) {
        Optional<subscribList> result = subrepo.getSubListByUseridAndTargetid(vo.getUserid(),vo.getAid());
        airUser articleUserEntity = urepo.findByUserId(vo.getUserid()).get();
        if (result.isPresent()){
            subrepo.delete(result.get());
            return "구독취소";
        }{
            subrepo.save(subscribList.builder().targetId(vo.getAid()).userid(articleUserEntity).build());
            return "구독";
        }
    }

    @Override
    public MySubInfo getFolloCount(subcard vo) {
        SubscribeRepository.getMySubInfo geted = subrepo.getFollwerFollwingCountByUserid(vo.getUserid()).get();
        MySubInfo result = new MySubInfo(geted);
        return result;
    }
}
