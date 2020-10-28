package com.ipn.mx.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipn.mx.modelo.dto.Evento;

public class EventoDAO {
  private static final String SQL_INSERT = "insert into Evento (nombreEvento, sede, fechaInicio, fechaTermino) values (?, ?, ?, ?)";
  private static final String SQL_UPDATE = "update Evento set nombreEvento = ?, sede = ?, fechaInicio = ?, fechaTermino = ? where idEvento = ?";
  private static final String SQL_DELETE = "delete from Evento where idEvento = ?";
  private static final String SQL_SELECT = "select * from Evento where idEvento = ?";
  private static final String SQL_SELECT_ALL = "select * from Evento";

  private Connection connection;
  
  private void getConnection()
  {
    String user = "root";
    String pass = "root";
    String url = "jdbc:mysql://localhost:3306/3cm9?serverTimezone=America/Mexico_City&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false";
    String driverMySQL = "com.mysql.cj.jdbc.Driver";

    connection = null;

    try {
      Class.forName(driverMySQL);
      connection = DriverManager.getConnection(url, user, pass);
    } catch (Exception err) {
      System.out.println(err);
    }
  }

  public void create(Evento evento) throws SQLException
  {
    getConnection();
    PreparedStatement ps = null;
    try {
      ps = connection.prepareStatement(SQL_INSERT);
      ps.setString(1, evento.getNombreEvento());
      ps.setString(2, evento.getSede());
      ps.setDate(3, evento.getFechaInicio());
      ps.setDate(4, evento.getFechaTermino());
      ps.executeUpdate();
    } finally {
      if(ps != null) ps.close();
      if(connection != null) connection.close();
    }
  }

  public void update(Evento evento) throws SQLException
  {
    getConnection();
    PreparedStatement ps = null;
    try {
      ps = connection.prepareStatement(SQL_UPDATE);
      ps.setString(1, evento.getNombreEvento());
      ps.setString(2, evento.getSede());
      ps.setDate(3, evento.getFechaInicio());
      ps.setDate(4, evento.getFechaTermino());
      ps.setInt(5, evento.getIdEvento());
      ps.executeUpdate();
    } finally {
      if(ps != null) ps.close();
      if(connection != null) connection.close();
    }
  }

  public void delete(Evento evento) throws SQLException
  {
    getConnection();
    PreparedStatement ps = null;
    try {
      ps = connection.prepareStatement(SQL_DELETE);
      ps.setInt(1, evento.getIdEvento());
      ps.executeUpdate();
    } finally {
      if(ps != null) ps.close();
      if(connection != null) connection.close();
    }
  }

  public List<Evento> findAll() throws SQLException
  {
    getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      ps = connection.prepareStatement(SQL_SELECT_ALL);
      rs = ps.executeQuery();
      List<Evento> results = getResults(rs);
      if(!results.isEmpty()) return results;
      else return null;
    } finally {
      if(rs != null) rs.close();
      if(ps != null) ps.close();
      if(connection != null) connection.close();
    }
  }

  public Evento findById(Evento evento) throws SQLException
  {
    getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      ps = connection.prepareStatement(SQL_SELECT);
      ps.setInt(1, evento.getIdEvento());
      rs = ps.executeQuery();
      List <Evento> results = getResults(rs);
      if(!results.isEmpty()) return (Evento) results.get(0);
      else return null;
    } finally {
      if(rs != null) rs.close();
      if(ps != null) ps.close();
      if(connection != null) connection.close();
    }
  }

  public List<Evento> getResults(ResultSet rs) throws SQLException
  {
    List<Evento> results = new ArrayList<Evento>();
    while(rs.next()){
      Evento evento = new Evento();
      evento.setIdEvento(rs.getInt("idEvento"));
      evento.setNombreEvento(rs.getString("nombreEvento"));
      evento.setSede(rs.getString("sede"));
      evento.setFechaInicio(rs.getDate("fechaInicio"));
      evento.setFechaTermino(rs.getDate("fechaTermino"));
      results.add(evento);
    }
    return results;
  }


  public static void main(String[] args) {
    Evento evento = new Evento();
    evento.setNombreEvento("Evento de prueba");
    evento.setSede("Sede de prueba");
    EventoDAO eventoDAO = new EventoDAO();
    try{
      eventoDAO.create(evento);
    } catch(SQLException e) {
      System.out.println(e);
    }
  }
}
