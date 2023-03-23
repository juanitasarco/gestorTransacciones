package com.brokis.Banco.controlador.DTO;

import com.brokis.Banco.modelo.Cuenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdDTO {

    public int id;
    public String tipoCuenta;
    public int saldo;
    public String fechaDeCreacion;

    public Cuenta toModel(){
        return new Cuenta(this.id,this.tipoCuenta,this.saldo,this.fechaDeCreacion);
    }


}
