package ds.pirate.backend.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class searchingDTO {
    private int page;
    private int listSize;
    private int pageListSize;
    private String keyword;

    public searchingDTO(){
        this.page = 1;
        this.listSize = 5;
        this.pageListSize = 5;
    }

    public Pageable getPageable() {
        return PageRequest.of(page - 1, listSize);
    }
}
