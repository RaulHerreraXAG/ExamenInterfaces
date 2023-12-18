package com.example.examendamdesarrollo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Coche {
    private String matricula;
    private String modelocoche;
    private LocalDate salida;
    private LocalDate entrega;
    private String tarifa;
    private Cliente cliente;
    private Integer coste;

    public Coche(String matricula, String modelocoche, Cliente cliente ,LocalDate salida, LocalDate entrega, String tarifa, Integer coste) {
        this.matricula = matricula;
        this.modelocoche = modelocoche;
        this.cliente = cliente;
        this.salida = salida;
        this.entrega = entrega;
        this.tarifa = tarifa;
        this.coste = coste;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelocoche() {
        return modelocoche;
    }


    public Integer getCoste() {
        return coste;
    }

    public void setCoste(Integer coste) {
        this.coste = coste;
    }

    public LocalDate getSalida() {
        return salida;
    }

    public LocalDate getEntrega() {
        return entrega;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setModelocoche(String modelocoche) {
        this.modelocoche = modelocoche;
    }


    public void setSalida(LocalDate salida) {
        this.salida = salida;
    }

    public void setEntrega(LocalDate entrega) {
        this.entrega = entrega;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }



}
