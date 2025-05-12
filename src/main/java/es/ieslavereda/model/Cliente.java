package es.ieslavereda.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Cliente implements Serializable, Comparable<Cliente> {
    private int id;
    private String nombre;
    private String apellidos;
    private String pais;
    private String mail;

    @Override
    public String toString() {
        return "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", pais='" + pais + '\'' +
                ", mail='" + mail + "'\n";
    }

    @Override
    public int compareTo(Cliente o) {

        if(apellidos.compareTo(o.getApellidos()) == 0)
            return nombre.compareTo(o.getNombre());

        return apellidos.compareTo(o.getApellidos());
    }
}
