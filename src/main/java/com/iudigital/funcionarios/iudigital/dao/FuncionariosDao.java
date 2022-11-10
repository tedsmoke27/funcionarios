/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iudigital.funcionarios.iudigital.dao;

import com.iudigital.funcionarios.iudigital.dominio.Funcionario;
import com.iudigital.funcionarios.iudigital.utils.ConectionDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Cesar
 */
public class FuncionariosDao {
    private static final String GET_FUNCIONARIOS="select * from funcionarios";
    private static final String CREATE_FUNCIONARIO="insert into funcionarios"
            +"(fk_id_sexo, fk_id_estadocivil, nombre, apellidos, cedulafuncionario, fechanacimiento, direccionfuncionario) "
            +"values (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_FUNCIONARIO_BY_ID="select * from funcionarios where id_funcionario= ?";
    private static final String UPDATE_FUNCIONARIO="update funcionarios set fk_id_sexo=?, fk_id_estadocivil=?, nombre= ?"
            +", apellidos= ?, fechanacimiento= ?, direccionfuncionario= ? where cedulafuncionario= ?";
    private static final String DELETE_FUNCIONARIO="delete from funcionarios where id_funcionario= ?";
    
    public void crear(Funcionario funcionario) throws SQLException{
        Connection conection = null;
        PreparedStatement preparedStatement= null;
        try {
            conection= ConectionDataBase.getConnection();
            preparedStatement= conection.prepareCall(CREATE_FUNCIONARIO);
            preparedStatement.setInt(1, funcionario.getSexo());
            preparedStatement.setInt(2, funcionario.getEstado_civil());
            preparedStatement.setString(3, funcionario.getNombre());
            preparedStatement.setString(4, funcionario.getApellidos());
            preparedStatement.setInt(5, funcionario.getCedulafuncionario());
            preparedStatement.setString(6, funcionario.getFechanacimiento());
            preparedStatement.setString(7, funcionario.getDireccion());
            preparedStatement.executeUpdate();
        } finally{
            if(conection!=null){
                conection.close();
            }
            if(preparedStatement!=null){
                preparedStatement.close();
            }
        }  
    }
    
    public  List<Funcionario> obtenerFuncionarios() throws SQLException{
        Connection conection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;
        List<Funcionario> funcionarios= new ArrayList<>(); 
        try{
            conection= ConectionDataBase.getConnection();
            preparedStatement= conection.prepareStatement(GET_FUNCIONARIOS);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                Funcionario funcionario= new Funcionario();
                funcionario.setId_funcionario(resultSet.getInt("id_funcionario"));
                funcionario.setSexo(resultSet.getInt("fk_id_sexo"));
                funcionario.setEstado_civil(resultSet.getInt("fk_id_estadocivil"));
                funcionario.setNombre(resultSet.getString("nombre"));
                funcionario.setApellidos(resultSet.getString("apellidos"));
                funcionario.setCedulafuncionario(resultSet.getInt("cedulafuncionario"));
                funcionario.setFechanacimiento(resultSet.getString("fechanacimiento"));
                funcionario.setDireccion(resultSet.getString("direccionfuncionario"));
                funcionarios.add(funcionario);
                
            }
            return funcionarios;
        }finally{
           if(conection!=null){
                conection.close();
            }
            if(preparedStatement!=null){
                preparedStatement.close();
            }
            if(resultSet!=null){
                resultSet.close();
            }
        }
    }
    
    public Funcionario obtenerFuncionario(int id) throws SQLException{
        Connection conection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;
        Funcionario funcionario= null;
        
        try{
             conection= ConectionDataBase.getConnection();
             preparedStatement= conection.prepareStatement(GET_FUNCIONARIO_BY_ID);
             preparedStatement.setInt(1, id);
             resultSet= preparedStatement.executeQuery();
             if(resultSet.next()){
                 funcionario= new Funcionario();
                 funcionario.setNombre(resultSet.getString("nombre"));
                funcionario.setApellidos(resultSet.getString("apellidos"));
                funcionario.setCedulafuncionario(resultSet.getInt("cedulafuncionario"));
                funcionario.setFechanacimiento(resultSet.getString("fechanacimiento"));
                funcionario.setDireccion(resultSet.getString("direccion"));
                 
             }
             return funcionario;
        } finally{
           if(conection!=null){
                conection.close();
            }
            if(preparedStatement!=null){
                preparedStatement.close();
            }
            if(resultSet!=null){
                resultSet.close();
            } 
        }
    }
    
    public void actualizar(int i, Funcionario funcionario) throws SQLException{
        Connection conection = null;
        PreparedStatement preparedStatement= null;
        try {
            conection= ConectionDataBase.getConnection();
            preparedStatement= conection.prepareStatement(UPDATE_FUNCIONARIO);
            preparedStatement.setInt(1, funcionario.getSexo());
            preparedStatement.setInt(2, funcionario.getEstado_civil());
            preparedStatement.setString(3, funcionario.getNombre());
            preparedStatement.setString(4, funcionario.getApellidos());
            preparedStatement.setString(5, funcionario.getFechanacimiento());
            preparedStatement.setString(6, funcionario.getDireccion());
            preparedStatement.setInt(7, funcionario.getCedulafuncionario());
            preparedStatement.executeUpdate();
        } finally{
            if(conection!=null){
                conection.close();
            }
            if(preparedStatement!=null){
                preparedStatement.close();
            }
        }  
    }
    
    public  void eliminar(int id) throws SQLException{
        Connection conection = null;
        PreparedStatement preparedStatement= null;
        
        try{
            conection= ConectionDataBase.getConnection();
            preparedStatement= conection.prepareStatement(DELETE_FUNCIONARIO);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }finally{
           if(conection!=null){
                conection.close();
            }
            if(preparedStatement!=null){
                preparedStatement.close();
            } 
        }
    }
}
