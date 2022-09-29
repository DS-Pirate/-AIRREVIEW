package ds.pirate.backend.service.UserService.UserImage;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ds.pirate.backend.entity.uImagesList;
import ds.pirate.backend.repository.UserImageListRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserImageServiceImpl implements UserImageService{
    
    private final UserImageListRepository uilrepo;

    
    @Override
    public String getUImageNameByUserid(Long userid) {
        Optional<uImagesList> entity = uilrepo.getByAiruser(userid);
        if(entity.isPresent()){
            return entity.get().getFileName();
        }else{
            return "basic.png";
        }
    }

}
