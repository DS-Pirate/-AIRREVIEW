package ds.pirate.backend.service;

import ds.pirate.backend.dto.UserApiDTO;

public interface UserService {
    
    Long register(UserApiDTO dto);

}
