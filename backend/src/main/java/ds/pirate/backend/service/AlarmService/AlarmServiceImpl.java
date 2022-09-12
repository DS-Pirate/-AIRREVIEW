package ds.pirate.backend.service.AlarmService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ds.pirate.backend.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService{
    private final AlarmRepository repo;
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
}
