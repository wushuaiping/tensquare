package io.wooo.tensquare.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.wooo.tensquare.common.util.IdWorker;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: wushuaiping
 * @date: 2018/11/28 3:04 PM
 * @description:
 */
@Entity
@Table(name = "tb_user")
@Setter
@Getter
public class User implements Serializable {

    private static final long serialVersionUID = 2159776798186632232L;

    @Id
    private String id;

    /**
     * 用户名
     */
    private String nickname;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 性别
     */
    private String sex;

    /**
     * 关注数
     */
    private Integer followCount;

    /**
     * 粉丝数
     */
    private Integer fansCount;

    /**
     * 在线时长
     */
    private Long online;

    /**
     * 注册日期
     */
    private LocalDateTime regDate;

    /**
     * 更新日期
     */
    private LocalDateTime lastModifiedDate;

    /**
     * 最后登录日期
     */
    private LocalDateTime lastLoginDate;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 微信
     */
    private String wechat;

    /**
     * qq
     */
    private String qq;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 兴趣
     */
    private String interest;

    /**
     * 个性
     */
    private String personality;

    /**
     * 签名
     */
    private String signature;

    /**
     * 是否删除
     */
    private String deleted;

    public void initUser(User user) {
        if (user == null) {
            user = new User();
        }
        final IdWorker instance = IdWorker.getInstance();
        user.setId(instance.nextIdStringValue());
        user.setDeleted("N");
        user.setFansCount(0);
        user.setFollowCount(0);
        user.setOnline(0L);
        final LocalDateTime now = LocalDateTime.now();
        user.setRegDate(now);
        user.setLastLoginDate(now);
        user.setLastModifiedDate(now);
    }
}
