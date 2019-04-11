package com.kakaopay.finance.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="INSTITUTE_DATA")
@IdClass(InstituteDataPK.class)
public class InstituteData {

    @Column(name="INSTITUTE_NAME")
    private String instituteName;


    @Id
    @Column(name="INSTITUTE_CODE")
    private String instituteCode;

}