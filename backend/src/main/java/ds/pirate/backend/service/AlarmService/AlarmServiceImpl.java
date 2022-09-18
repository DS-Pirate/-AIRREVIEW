package ds.pirate.backend.service.AlarmService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.acomments;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.alarm;
import ds.pirate.backend.repository.AlarmRepository;
import ds.pirate.backend.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService{
    private final AlarmRepository repo;
    private final ArticleRepository arepo;
    @Override
    public List<Object> getAlarmListByUserid(Long userid) {
        Optional <List<Object>>result = repo.getAlarmInfoByUserid(userid);
        if (result.isPresent()){
            return result.get();
            //이렇게하면.. 보안적으로 괜찮은지는 모르겠찌만.. 일단 .. 그래도 ..
        } else{
            return null;
        }
    }

    @Override
    public void checked(Long arid) {
        alarm result = repo.findById(arid).get();
        result.updateChecked(true);
        repo.save(result);

    }

    @Override
    public void addAlarm(acomments entity) {
        alarm alar = alarm.builder()
        .articleId(ArticlesList.builder().aid(entity.getArticles().getAid()).build())
        .commentId(acomments.builder().cid(entity.getCid()).build())
        .whoUser(airUser.builder().userid(entity.getAiruser().getUserid()).build())
        .toUser(airUser.builder().userid(arepo.findByAid(entity.getArticles().getAid()).getAUser()).build())
        .build();
        
        repo.save(alar);
    }

}
