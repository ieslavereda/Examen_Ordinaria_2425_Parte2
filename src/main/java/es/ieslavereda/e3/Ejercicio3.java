package es.ieslavereda.e3;

import es.ieslavereda.model.Cliente;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Ejercicio3 {
    public static void mostrarOrdenNatural(List<Cliente> clientes){

        Collections.sort(clientes);

        System.out.println("Listado por orden natural\n" + clientes);

    }

    public static void mostrarPais(List<Cliente> clientes) throws IOException {

        Collections.sort(clientes, (o1, o2) -> {
            if(o1.getPais().equals(o2.getPais()))
                return o1.compareTo(o2);

            return o1.getPais().compareTo(o2.getPais());
        });

        System.out.println("Listado por paises\n" + clientes);

    }
}
