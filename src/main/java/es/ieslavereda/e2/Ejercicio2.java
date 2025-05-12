package es.ieslavereda.e2;

import es.ieslavereda.model.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio2 {

    public static List<Cliente> cargarFichero() throws IOException {

        List<Cliente> clientes = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("fichero.csv"))) {
            String linea;
            while ((linea=br.readLine())!=null) {
                String[] datos = linea.split(";");
                clientes.add(Cliente.builder()
                        .id(Integer.parseInt(datos[0]))
                        .nombre(datos[1])
                        .apellidos(datos[2]+" "+datos[3])
                        .mail(datos[4])
                        .pais(datos[5])
                        .build()
                );

            }
        }

        return clientes;
    }

    public static void guardarClientes(List<Cliente> clientes) throws IOException {

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("fichero.dat"))) {
            oos.writeObject(clientes);
        }

    }
    public static void checkClientes() throws IOException, ClassNotFoundException {

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("fichero.dat"))) {
            List<Cliente> clientes = (List<Cliente>) ois.readObject();
            System.out.println(clientes);
        }

    }
}
