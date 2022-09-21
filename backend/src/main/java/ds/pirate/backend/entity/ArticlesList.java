package ds.pirate.backend.entity;

import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"tags", "images", "cgroup"})
public class ArticlesList extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;

    @Column(nullable = false)
    private String atitle;

    @Lob
    @Column(nullable = false)
    private byte[] context;


    @Column(nullable = false)
    private boolean opend;

    @Column(nullable = false)
    private boolean shareable;

    @Column(nullable = false)
    private Long aUser;

    @Column
    @ColumnDefault("0")
    private Long opencount;

    @Builder.Default
    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<HashTags> tags = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ImagesList> images = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<acomments> cgroup = new ArrayList<>();


//이거 참고하기!!
    public String updateContextToString(byte[] context){
        String result = new String(context, Charset.forName("utf8"));
        return result;
    }
}