/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iudigital.funcionarios.iudigital.controller;

import com.iudigital.funcionarios.iudigital.dao.FuncionariosDao;
import com.iudigital.funcionarios.iudigital.dominio.Funcionario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Cesar
 */
public class FuncionariosController {
    private FuncionariosDao funcionarioDao;
    
    public FuncionariosController(){
        funcionarioDao= new FuncionariosDao();
    }
    
    public  void crearFuncionario(Funcionario funcionario) throws SQLException{
        funcionarioDao.crear(funcionario);
    }
    
    public List<Funcionario> obtenerFuncionarios()throws SQLException{
      return funcionarioDao.obtenerFuncionarios();
    }
    
    public Funcionario obtenerFuncionario(int id) throws SQLException{
        return funcionarioDao.obtenerFuncionario(id);
    }
    
    public void actualizarFuncionario(int id, Funcionario funcionario) throws SQLException{
        funcionarioDao.actualizar(id, funcionario);
    }
    
    public void eliminarFuncionario(int id) throws SQLException{
        funcionarioDao.eliminar(id);
    }
    
}
