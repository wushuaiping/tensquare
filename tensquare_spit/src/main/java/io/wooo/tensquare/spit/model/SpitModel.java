package io.wooo.tensquare.spit.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author: wushuaiping
 * @date: 2018/11/21 2:52 PM
 * @description:
 */
@Setter
@Getter
public class SpitModel {
    private String _id;

    private String content;

    private LocalDateTime publishtime;

    private String userid;

    private String nickname;

    private Integer visits;

    private Integer thumbup;

    private Integer share;

    private Integer comment;

    private String state;

    private String parentid;

    private void setPublishtime(Long timestamp) {
        if (timestamp != null) {
            final LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timestamp / 1000, 0, ZoneOffset.ofHours(8));
            publishtime = localDateTime;
        }
    }

}
