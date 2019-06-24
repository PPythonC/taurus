package com.example.taurus.model.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "taurus_resource")
@EntityListeners(AuditingEntityListener.class)
public class Resources implements Serializable {
    private static final long serialVersionUID = 3060117944880138064L;
    /**
     * 资源编号
     */
    @Id
    @GeneratedValue
    private Long resourcesId;
    /**
     * 资源名称
     */
    private String resourcesName;
    /**
     * 资源路径
     */
    private String resourcesPath;
    /**
     * 资源缩略图路径
     */
    private String resourcesSmallPath;
    /**
     * 资源类型
     */
    private String resourcesType;
    /**
     * 资源后缀
     */
    private String resourcesSuffix;
    /**
     * 上传日期
     */
    @CreatedDate
    private Date resourcesCreated;
    /**
     * 资源大小
     */
    private String resourcesSize;
    /**
     * 附件宽度
     */
    private String resourcesWh;
    /**
     * 资源存储地址
     */
    private String resourcesLocation;
    /**
     * 资源来源 0：上传 1：外部链接
     */
    private Integer resourcesOrigin = 0;
    /**
     * 发表用户
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * 标签
     */
    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinTable(name = "taurus_resources_tags",joinColumns = {@JoinColumn(name = "resources_id",nullable = false)},
    inverseJoinColumns = {@JoinColumn(name = "tag_id",nullable = false)})
    private List<Tag> tags=new ArrayList<>();
}
