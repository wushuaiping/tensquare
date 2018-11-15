package io.wooo.tensquare.qa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 4:32 PM
 * @description:
 */
@Setter
@Getter
@Entity
@Table(name = "tb_reply")
public class Reply {

    @Id
    private String id;

    /**
     * 回答内容
     */
    private String content;

    /**
     * 创建日期
     */
    private LocalDateTime createtime;

    /**
     * 更新日期
     */
    private LocalDateTime updatetime;

    /**
     * 回答人id
     */
    private String userid;

    /**
     * 回答人昵称
     */
    private String nickname;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "problem_id")
    private Problem problem;
}
