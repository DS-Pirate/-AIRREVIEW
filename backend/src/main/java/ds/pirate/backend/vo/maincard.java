package ds.pirate.backend.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class maincard {
    String airName;
    Long aid;
    String atitle;
    byte[] context;
    LocalDateTime regDate;
    boolean opend;
}

