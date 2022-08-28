package ds.pirate.backend.service.UserService.UserImage;

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
        uImagesList entity = uilrepo.getByAiruser(userid);
        return entity.getFileName();
    }

}
