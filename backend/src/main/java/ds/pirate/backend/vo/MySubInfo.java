package ds.pirate.backend.vo;

import ds.pirate.backend.repository.SubscribeRepository;
import lombok.Data;


@Data
public class MySubInfo {

    private Long myuserid;
    private Long userid;
    private String airName;
    private Long post;
    private Long following;
    private Long follower;

    public MySubInfo(SubscribeRepository.getMySubInfo si){
        this.myuserid = si.getMyuserid();
        this.userid = si.getUserid();
        this.airName = si.getAirName();
        this.post = si.getPost();
        this.following = si.getFollowing();
        this.follower = si.getFollower();
    }
}
