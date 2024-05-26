package demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MultipleRequestDto {

    private int first;
    private int second;
}
