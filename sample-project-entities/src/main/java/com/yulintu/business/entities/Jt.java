package com.yulintu.business.entities;

import com.yulintu.thematic.data.validation.Insert;
import com.yulintu.thematic.data.validation.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "tb_dat_jt")
public class Jt {
    @Id
    @NotBlank(groups = {Update.class})
    @Column(updatable = false)
    @ApiModelProperty(hidden = true)
    private String id = UUID.randomUUID().toString();

    @NotBlank(groups = {Insert.class, Update.class})
    private String mc;

    @NotBlank(groups = {Insert.class, Update.class})
    private String bh;

    private String dz;

    private String bz;
}
