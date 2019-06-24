package com.example.taurus.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 系统设置
 */
@Data
@Entity
@Table(name = "taurus_options")
public class Options implements Serializable {
    private static final long serialVersionUID = -4065369084341893446L;
    /**
     * 名称
     */
    @Id
    @Column(length = 127)
    private String optionName;
    /**
     * 值
     */
    @Lob
    private String optionValue;
}
