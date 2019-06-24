package com.example.taurus.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 *   用户信息
 * </pre>
 * @author :Telegram
 * @date
 */
@Data
@Entity
@Table(name="taurus_user")
public class User implements Serializable {
    private static final long serialVersionUID=-5144055068797033748L;
    /**
     * 主键
     * 编号
     */
    @Id
    @GeneratedValue
    private Long userId;
    /**
     * 用户名
     * 不能为空
     * 为了安全不使用json传输
     */
    @NotBlank(message = "用户名为空！！！")
    @JsonIgnore
    private String userName;
    /**
     * 显示名称
     */
    private String userDisplayName;
    /**
     * 密码
     *
     */
    @JsonIgnore
    private String userPass;
    /**
     * 邮箱
     */
    @Email(message = "请使用正确的邮箱地址！！！")
    private String userEmail;
    /**
     * 头像
     */
    private String userAvatar;
    /**
     * 说明
     */
    private String userDesc;
    /**
     * 是否禁用登陆
     */
    @JsonIgnore
    private String loginEnable="true";
    /**
     * 最后登陆时间
     */
    @JsonIgnore
    private Date loginLast;
    /**
     * 登陆错误次数
     */
    private Integer loginError=0;
}
