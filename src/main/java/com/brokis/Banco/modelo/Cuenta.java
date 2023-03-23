package com.brokis.Banco.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity(name = "ACCOUNT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = " ")
    @Column
    private String TYPE;
    @Column
    private int MONEY=0;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date DATE_CREATED;
    @Column
    private int USER;


}
