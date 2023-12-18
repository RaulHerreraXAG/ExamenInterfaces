package com.example.examendamdesarrollo;

import java.util.ArrayList;

public class Session {
    private static Cliente clienteActual = null;

    private static ArrayList<Cliente> clientes = new ArrayList<>(0);

    private static Coche cocheActual = null;

    private static ArrayList<Coche> Coches = new ArrayList<>(0);


    private static Integer pos = null;

    public static Cliente getClienteActual() {
        return clienteActual;
    }

    public static void setClienteActual(Cliente clienteActual) {
        Session.clienteActual = clienteActual;
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static void setClientes(ArrayList<Cliente> clientes) {
        Session.clientes = clientes;
    }

    public static Coche getCocheActual() {
        return cocheActual;
    }

    public static void setCocheActual(Coche cocheActual) {
        Session.cocheActual = cocheActual;
    }

    public static ArrayList<Coche> getCoches() {
        return Coches;
    }

    public static void setCoches(ArrayList<Coche> coches) {
        Coches = coches;
    }

    public static Integer getPos() {
        return pos;
    }

    public static void setPos(Integer pos) {
        Session.pos = pos;
    }
}
