package com.example.demo.dto.request;

import jakarta.persistence.Id;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
@Builder
public class QnaAnswerRequestDTO {


    private Long qnaNo;

    private String qAnswer;

}
