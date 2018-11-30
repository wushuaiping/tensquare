package io.wooo.tensquare.qa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 4:27 PM
 * @description:
 */
@Setter
@Getter
@Entity
@Table(name = "tb_problem")
public class Problem {

    @Id
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建日期
     */
    private LocalDateTime createtime;

    /**
     * 修改日期
     */
    private LocalDateTime updatetime;

    /**
     * 用户id
     */
    private String userid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 浏览量
     */
    private Long visits;

    /**
     * 点赞数
     */
    private Long thumbup;

    /**
     * 回复数
     */
    private Long replyCount;

    /**
     * 是否解决
     */
    private String solve;

    /**
     * 问题回答
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reply> reply;

    /**
     * 问题关联的标签id
     */
    private String labelId;

    public void initProblem(Problem problem) {
        if (problem == null) {
            problem = new Problem();
        }
        final LocalDateTime now = LocalDateTime.now();
        problem.setCreatetime(now);
        problem.setUpdatetime(now);
        problem.setVisits(0L);
        problem.setThumbup(0L);
        problem.setReplyCount(0L);
    }
}
