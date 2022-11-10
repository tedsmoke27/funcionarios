/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iudigital.funcionarios.iudigital.presentacion;

import com.iudigital.funcionarios.iudigital.controller.FuncionariosController;
import com.iudigital.funcionarios.iudigital.dominio.Funcionario;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Cesar
 */
public class Main {
    
    public static void obtenerFuncionarios(FuncionariosController funcionariosController){
        try{
          List<Funcionario> funcionarios= funcionariosController.obtenerFuncionarios();
        if(funcionarios.isEmpty()){
            System.out.println("NO hay datos");
        }else{
            funcionarios.forEach(funcionario ->{
                System.out.println("id funcionario "+ funcionario.getId_funcionario());
                System.out.println("id sexo "+ funcionario.getSexo());
                System.out.println("id estado civil "+ funcionario.getEstado_civil());
                System.out.println("Nombre "+ funcionario.getNombre());
                System.out.println("Apellidos "+ funcionario.getApellidos());
                System.out.println("Cedula "+ funcionario.getCedulafuncionario());
                System.out.println("Fecha de nacimiento "+ funcionario.getFechanacimiento());
                System.out.println("Direccion "+ funcionario.getDireccion());
                System.out.println("---------");
            });
        }  
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static void eliminarFunc(FuncionariosController funcionariosController){
        try{
           Scanner sc= new Scanner(System.in);
           System.out.println("Digita id funcionario");
           int id = sc.nextInt();
           funcionariosController.eliminarFuncionario(id);
           System.out.println("Funcionario eliminado!");
        
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        obtenerFuncionarios(funcionariosController);
    }
    
    public static void crearFuncionario(FuncionariosController funcionarioControler){
            Scanner sc= new Scanner(System.in);
            System.out.println("Digita el nombre");
            String nombre = sc.nextLine();
            System.out.println("El nombre es "+nombre);
            System.out.println("-------------");
            
            System.out.println("Digita los apellidos");
            String apellidos = sc.nextLine();
            System.out.println("Los apellido son "+apellidos);
            System.out.println("-------------");
            
            System.out.println("Digita la cedula");
            int cedula = sc.nextInt();
            sc.nextLine();
            System.out.println("La cedula es "+cedula);
            System.out.println("-------------");
            
            System.out.println("Digita la fecha de nacimiento");
            String fechanacimiento = sc.nextLine();
            System.out.println("La fecha de nacimiento es "+fechanacimiento);
            System.out.println("-------------");
            
            System.out.println("Digita la direccion");
            String direccion = sc.nextLine();
            System.out.println("La direccion es "+direccion);
            System.out.println("-------------");
            
            System.out.println("Digita el estado civil.");
            int estado = sc.nextInt();
            System.out.println("El estado civil es "+estado);
            System.out.println("-------------");
            
            System.out.println("Digita el sexo");
            int sexo = sc.nextInt();
            System.out.println("El sexo es "+sexo);
            System.out.println("-------------");
            
            Funcionario funcionario = new Funcionario();
            funcionario.setEstado_civil(estado);
            funcionario.setSexo(sexo);
            funcionario.setNombre(nombre);
            funcionario.setApellidos(apellidos);
            funcionario.setCedulafuncionario(cedula);
            funcionario.setFechanacimiento(fechanacimiento);
            funcionario.setDireccion(direccion);
            try{
                funcionarioControler.crearFuncionario(funcionario);
                System.out.println("Funcionario creado!");
            }catch (SQLException ex){
                ex.printStackTrace();
            } 
            obtenerFuncionarios(funcionarioControler);
    }
    
    public static void editarFuncionario(FuncionariosController funcionarioControler){
            Scanner sc= new Scanner(System.in);
            System.out.println("Digita el nombre");
            String nombre = sc.nextLine();
            System.out.println("El nombre es "+nombre);
            System.out.println("-------------");
            
            System.out.println("Digita los apellidos");
            String apellidos = sc.nextLine();
            System.out.println("Los apellido son "+apellidos);
            System.out.println("-------------");
            
            System.out.println("Digita la cedula");
            int cedula = sc.nextInt();
            System.out.println("La cedula es "+cedula);
            sc.nextLine();
            System.out.println("-------------");
            
            System.out.println("Digita la fecha de nacimiento");
            String fechanacimiento = sc.nextLine();
            System.out.println("La fecha de nacimiento es "+fechanacimiento);
            System.out.println("-------------");
            
            System.out.println("Digita la direccion");
            String direccion = sc.nextLine();
            System.out.println("La direccion es "+direccion);
            System.out.println("-------------");
            
            System.out.println("Digita el estado civil.");
            int estado = sc.nextInt();
            System.out.println("El estado civil es "+estado);
            System.out.println("-------------");
            
            System.out.println("Digita el sexo");
            int sexo = sc.nextInt();
            System.out.println("El sexo es "+sexo);
            System.out.println("-------------");
            
            Funcionario funcionario = new Funcionario();
            funcionario.setEstado_civil(estado);
            funcionario.setSexo(sexo);
            funcionario.setNombre(nombre);
            funcionario.setApellidos(apellidos);
            funcionario.setCedulafuncionario(cedula);
            funcionario.setFechanacimiento(fechanacimiento);
            funcionario.setDireccion(direccion);
            funcionario.setId_funcionario(1);
            try{
                funcionarioControler.actualizarFuncionario(1, funcionario);
                System.out.println("Funcionario actualizado!");
            }catch (SQLException ex){
                ex.printStackTrace();
            }  
            obtenerFuncionarios(funcionarioControler);
    }
    
    public static void main(String[] args) {
        FuncionariosController funcionariosController= new FuncionariosController();
        obtenerFuncionarios(funcionariosController);
       // crearFuncionario(funcionariosController);
       // editarFuncionario(funcionariosController);
       // eliminarFunc(funcionariosController);
    }
}
