package io.wooo.tensquare.spit.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: wushuaiping
 * @date: 2018/11/21 11:55 AM
 * @description:
 */
@Document(collection = "spit")
@Setter
@Getter
public class Spit implements Serializable {

    private static final long serialVersionUID = 572083229467903587L;

    @Id
    private String _id;

    private String content;

    private LocalDateTime publishtime;

    private String userid;

    private String nickname;

    private Integer visits = 0;

    private Integer thumbup = 0;

    private Integer share = 0;

    private Integer comment = 0;

    private String state;

    private String parentid;

}
