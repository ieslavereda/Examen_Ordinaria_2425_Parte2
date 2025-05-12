package es.ieslavereda.e1;

import es.ieslavereda.model.bd.MyDataSource;
import es.ieslavereda.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements IClienteRepository {

    @Override
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection con = MyDataSource.getDataSource().getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                clientes.add(
                        Cliente.builder()
                                .id(rs.getInt("id"))
                                .nombre(rs.getString("nombre"))
                                .apellidos(rs.getString("apellidos"))
                                .mail(rs.getString("mail"))
                                .pais(rs.getString("pais"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    @Override
    public Cliente getCliente(int id) throws Exception {
        String sql = "SELECT * FROM cliente WHERE id = ?";

        try (Connection con = MyDataSource.getDataSource().getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                return Cliente.builder()
                        .id(rs.getInt("id"))
                        .nombre(rs.getString("nombre"))
                        .apellidos(rs.getString("apellidos"))
                        .mail(rs.getString("mail"))
                        .pais(rs.getString("pais"))
                        .build();

            } else
                throw new Exception("El cliente con id " + id + " no existe");
        }
    }

    @Override
    public Cliente updateCliente(Cliente cliente) throws Exception {
        String sql = "UPDATE cliente " +
                "SET nombre = ?, apellidos = ?, mail = ?, pais = ? " +
                "WHERE id = ?";
        try (Connection con = MyDataSource.getDataSource().getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellidos());
            stmt.setString(3, cliente.getMail());
            stmt.setString(4, cliente.getPais());
            stmt.setInt(5, cliente.getId());

            if (stmt.executeUpdate() == 0) {
                throw new Exception("El cliente no existe");
            } else
                return cliente;

        } catch (SQLException e) {
            throw new Exception("Se ha producido un error en la BD.");
        }

    }

    @Override
    public Cliente deleteCliente(int id) throws Exception {
        String sql = "DELETE FROM cliente " +
                "WHERE id = ?";

        Cliente cliente = getCliente(id);

        try (Connection con = MyDataSource.getDataSource().getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            if (stmt.executeUpdate() == 0)
                throw new Exception("El cliente con id " + id + " no existe");
            else
                return cliente;

        } catch (SQLException e) {
            throw new Exception("Se ha producido un error en la BD.");
        }
    }

    @Override
    public Cliente insertCliente(Cliente cliente) throws Exception {
        String sql = "INSERT INTO cliente VALUES(?,?,?,?,?) ";

        Cliente c = null;
        try {
            c = getCliente(cliente.getId());
        } catch (Exception e) {}

        if (c != null) throw new Exception("El cliente con id " + cliente.getId() + " ya existe");

        try (Connection con = MyDataSource.getDataSource().getConnection()) {

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, cliente.getId());
            pstmt.setString(2, cliente.getNombre());
            pstmt.setString(3, cliente.getApellidos());
            pstmt.setString(4, cliente.getMail());
            pstmt.setString(5, cliente.getPais());

            pstmt.executeUpdate();

            return cliente;

        } catch (SQLException e) {
            throw new Exception("Se ha producido un error en la BD.");
        }
    }

    @Override
    public String obtenerMail(int id) throws Exception {
        String sql = "{ call obtenerMail(?,?) }";


        try (Connection con = MyDataSource.getDataSource().getConnection()) {

            CallableStatement cstmt = con.prepareCall(sql);
            cstmt.setInt(2, id);
            cstmt.registerOutParameter(1, Types.VARCHAR);

            cstmt.execute();

            return cstmt.getString(1);

        } catch (SQLException e) {
            throw new Exception("Se ha producido un error en la BD.");
        }
    }
}
