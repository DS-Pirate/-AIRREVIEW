package ds.pirate.backend.service.UserService;

import ds.pirate.backend.dto.UserApiDTO;

public interface UserService {
    
    Long register(UserApiDTO dto);

}
