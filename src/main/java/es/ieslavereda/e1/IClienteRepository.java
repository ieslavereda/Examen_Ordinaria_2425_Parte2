package es.ieslavereda.e1;
import es.ieslavereda.model.Cliente;

import java.util.List;

public interface IClienteRepository {

    List<Cliente> getAllClientes();
    Cliente getCliente(int id) throws Exception;
    Cliente updateCliente(Cliente cliente) throws Exception;
    Cliente deleteCliente(int id) throws Exception;
    Cliente insertCliente(Cliente cliente) throws Exception;

    String obtenerMail(int id) throws Exception;
}