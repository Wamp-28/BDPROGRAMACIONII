package org.example.dao;

import org.example.model.Profesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAOImpl implements ProfesorDAO {
    private final Connection connection;

    // Constructor que recibe la conexión como parámetro
    public ProfesorDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Profesor profesor) {
        String sql = "INSERT INTO profesores (cedula, nombre, apellido, edad, profesion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, profesor.getCedula());
            statement.setString(2, profesor.getNombre());
            statement.setString(3, profesor.getApellido());
            statement.setInt(4, profesor.getEdad());
            statement.setString(5, profesor.getProfesion());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Profesor leer(String cedula) {
        String sql = "SELECT * FROM profesores WHERE cedula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cedula);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Profesor(
                        resultSet.getString("cedula"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getInt("edad"),
                        resultSet.getString("profesion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Profesor profesor) {
        String sql = "UPDATE profesores SET nombre = ?, apellido = ?, edad = ?, profesion = ? WHERE cedula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, profesor.getNombre());
            statement.setString(2, profesor.getApellido());
            statement.setInt(3, profesor.getEdad());
            statement.setString(4, profesor.getProfesion());
            statement.setString(5, profesor.getCedula());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String cedula) {
        String sql = "DELETE FROM profesores WHERE cedula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cedula);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Profesor> listar() {
        List<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT * FROM profesores";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                profesores.add(new Profesor(
                        resultSet.getString("cedula"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getInt("edad"),
                        resultSet.getString("profesion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profesores;
    }
}
