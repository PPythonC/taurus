package com.example.taurus.model.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "taurus_logs")
public class Logs implements Serializable {
    private static final long serialVersionUID = -2571815432301283171L;
    /**
     * id
     */
    @Id
    @GeneratedValue
    private Long logId;
    /**
     * 标题
     */
    private String logTitle;
    /**
     * 内容
     */
    private String logContent;
    /**
     * 产生日志的ip
     */
    private String logIp;
    /**
     * 日志的创建时间
     */
    @CreatedDate
    private Date logCreate;

    public Logs() {

    }
}
