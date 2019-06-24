package com.example.taurus.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "taurus_comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment implements Serializable {
    private static final long serialVersionUID = -6639021627094260505L;
    /**
     * 评论id自增
     */
    @Id
    @GeneratedValue
    private Long commentId;
    @ManyToOne(targetEntity = Resources.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "resources_id")
    @JsonIgnore
    private Resources resources;
    @NotBlank(message = "评论用户的用户名不能为空")
    private String commentAuthor;
    @Email(message = "格式不正确")
    @JsonIgnore
    private String commentAuthorEmail;
    @JsonIgnore
    private String commentAuthorIp;
    @CreatedDate
    private Date commentDate;
    @NotBlank(message = "评论不能为空")
    @Lob
    private String commentContent;
    @Column(length = 512)
    private String commentAgent;

    private Long commentParent = 0L;

    private Integer isAdmin;
    @Transient
    private List<Comment> childComments;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date getCommentDate() {
        return commentDate;
    }
}
