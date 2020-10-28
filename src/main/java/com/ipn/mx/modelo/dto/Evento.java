package com.ipn.mx.modelo.dto;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

public class Evento implements Serializable{
  private Integer idEvento;
  private String nombreEvento;
  private String sede;
  private Date fechaInicio;
  private Date fechaTermino;

  public Evento() {
  }

  public Integer getIdEvento() {
    return idEvento;
  }

  public void setIdEvento(Integer idEvento) {
    this.idEvento = idEvento;
  }

  public String getNombreEvento() {
    return nombreEvento;
  }

  public void setNombreEvento(String nombreEvento) {
    this.nombreEvento = nombreEvento;
  }

  public String getSede() {
    return sede;
  }

  public void setSede(String sede) {
    this.sede = sede;
  }

  public Date getFechaInicio() {
    return fechaInicio;
  }

  public void setFechaInicio(Date fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  public Date getFechaTermino() {
    return fechaTermino;
  }

  public void setFechaTermino(Date fechaTermino) {
    this.fechaTermino = fechaTermino;
  }

  @Override
  public String toString() {
    return "Evento [fechaInicio=" + fechaInicio + ", fechaTermino=" + fechaTermino + ", idEvento=" + idEvento
        + ", nombreEvento=" + nombreEvento + ", sede=" + sede + "]";
  }

  public static void main(String[] args) {
    Evento e = new Evento();
    e.setIdEvento(1);
    e.setNombreEvento("Expo Escom");
    e.setSede("Auditorio Escom");

    System.out.println(e);

    String user = "root";
    String pass = "root";
    String url = "jdbc:mysql://localhost:3306/3cm9?serverTimezone=America/Mexico_City&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false";
    String driverMySQL = "com.mysql.cj.jdbc.Driver";

    Connection conexion = null;

    try {
      Class.forName(driverMySQL);
      conexion = DriverManager.getConnection(url, user, pass);
    } catch (Exception err) {
      System.out.println(err);
    }



  }

  private static void insertar (Connection conexion, Evento e)
  {
    PreparedStatement ps = null;
    String SQL_INSERT = "insert into evento (nombreEvento, sede, fechaInicio, fechaTermino) values (?, ?, ?, ?)";
    try {
      ps = conexion.prepareStatement(SQL_INSERT);
      ps.setString(1, e.getNombreEvento());
      ps.setString(2, e.getSede());
      ps.setDate(3, e.getFechaInicio());
      ps.setDate(4, e.getFechaTermino());
      ps.executeUpdate();
    } catch (Exception err) {
      System.out.println(err);
    }
  }

  private static void update (Connection conexion, Evento e)
  {
    PreparedStatement ps = null;
    String SQL_UPDATE = "update Evento set nombreEvento=?, sede=?, fechaInicio=?, fechaTermino=?, where idEvento = ?";
    try {
      ps = conexion.prepareStatement(SQL_UPDATE);
      ps.setString(1, e.getNombreEvento());
      ps.setString(2, e.getSede());
      ps.setDate(3, e.getFechaInicio());
      ps.setDate(4, e.getFechaTermino());
      ps.setInt(5, e.getIdEvento());
      ps.executeUpdate();
    } catch (Exception err) {
      System.out.println(err);
    }
  }

  private static void delete (Connection conexion, int idEvento)
  {
    PreparedStatement ps = null;
    String SQL_DELETE = "delete from Evento where idEvento = ?";
    try {
      ps = conexion.prepareStatement(SQL_DELETE);
      ps.setInt(1, idEvento);
      ps.executeUpdate();
    } catch (Exception err) {
      System.out.println(err);
    }
  }

  private static void findByIdEvento (Connection conexion, int idEvento)
  {
    PreparedStatement ps = null;
    ResultSet rs = null;
    String SQL_SELECT = "select * from Evento where idEvento = ?";
    try {
      ps = conexion.prepareStatement(SQL_SELECT);
      ps.setInt(1, idEvento);
      rs = ps.executeQuery();
      Evento evento = new Evento();
      while(rs.next())
      {
        evento.setIdEvento(rs.getInt("idEvento"));
        evento.setNombreEvento(rs.getString("nombreEvento"));
      }
    } catch (Exception err) {
      System.out.println(err);
    }
  }

}


