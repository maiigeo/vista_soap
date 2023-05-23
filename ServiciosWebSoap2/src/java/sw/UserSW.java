/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author HP
 */
@WebService(serviceName = "UserSW")
public class UserSW {

    ArrayList<Usuarios> lisUsuarios = new ArrayList<>();
    
    Usuarios usuarios;

    @WebMethod(operationName = "Ingresar")
    public String ingresar(@WebParam(name = "usuario") String user, @WebParam(name = "contrase") String password) {

        String mensaje = null;

        for (int i = 0; i < lisUsuarios.size(); i++) {
            if (lisUsuarios.get(i).getUsuario().equals(user)) {
                if (lisUsuarios.get(i).getUsuario().equals(user) && lisUsuarios.get(i).getPassword().equals(password)) {
                    mensaje = "Inicio sesión correctamente";
                } else {
                    mensaje = "Contraseña no coincide con su registro ";
                }
            } else {
                mensaje = "Este usuario no existe";
            }

        }
        return mensaje;

    }

    @WebMethod(operationName = "Registrar")
    public String registrar(@WebParam(name = "usuario") String user, @WebParam(name = "contrase") String password, @WebParam(name = "contrase2") String password2, @WebParam(name = "Saldo") Integer saldo) {
        String mensaje = ":(";
        Boolean est = false;
        if (password.equals(password2)) {

            for (int i = 0; i < lisUsuarios.size(); i++) {

                if (lisUsuarios.get(i).getUsuario().equals(user)) {
                    mensaje = "Este usuario ya existe" + user;
                    est = true;
                }
            }

            if (!est) {
                usuarios = new Usuarios();
                usuarios.setUsuario(user);
                usuarios.setPassword(password);
                usuarios.setSaldo(saldo);
                lisUsuarios.add(usuarios);
                mensaje = "Usuario registrado exitosamente" + user;
            }
        } else {
            System.out.println("contraseña incorrecta");
        }

        return mensaje;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Deposito")
    public String Deposito(@WebParam(name = "cantidad") Integer cantidad, @WebParam(name = "usuario") String user) {
        int canti = 0;
        String mensaje = null;

        for (int i = 0; i < lisUsuarios.size(); i++) {
            if (lisUsuarios.get(i).getUsuario().equals(user)) {
                Usuarios usuarios = new Usuarios();
                canti = lisUsuarios.get(i).getSaldo() + cantidad;
                lisUsuarios.get(i).setSaldo(canti);
                mensaje = "Depósito efectivo /n Su saldo actual es: " + canti;
            }

        }
        return mensaje;
    }

    @WebMethod(operationName = "Retiro")
    public String Retiro(@WebParam(name = "cantidad") Integer cantidad, @WebParam(name = "usuario") String user) {
        int canti2 = 0;
        String mensaje = null;

        for (int i = 0; i < lisUsuarios.size(); i++) {
            if (lisUsuarios.get(i).getUsuario().equals(user)) {
                if (lisUsuarios.get(i).getSaldo() > cantidad) {
                    canti2 = lisUsuarios.get(i).getSaldo() - cantidad;
                    lisUsuarios.get(i).setSaldo(canti2);
                    mensaje = "Retiro realizado con éxito /n Su saldo actual es: " + canti2;
                }
            }

        }
        return mensaje;
    }
    
    @WebMethod(operationName = "Saldo")
    public int Saldo(@WebParam(name = "usuario") String user) {
        int saldo=0;
        
        for (int i = 0; i < lisUsuarios.size(); i++) {
            if (lisUsuarios.get(i).getUsuario().equals(user)) {
                saldo=lisUsuarios.get(i).getSaldo();
            }
        }
        return saldo;
        
    }

}
