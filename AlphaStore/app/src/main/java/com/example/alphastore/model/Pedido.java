package com.example.alphastore.model;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private int id;
    private Date date;
    private Long total;
    private String obs;

    private Cliente cliente;
    private ArrayList<Produto> produtos;

    public Pedido(int id, Date date, Long total, String obs, Cliente cliente, ArrayList<Produto> produtos) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.obs = obs;
        this.cliente = cliente;
        this.produtos = produtos;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getObs() {
        return this.obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
