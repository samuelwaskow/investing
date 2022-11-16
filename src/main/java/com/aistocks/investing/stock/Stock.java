package com.aistocks.investing.stock;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String ticker;

    /**
     * Default Constructor
     */
    protected Stock() {
        super();
    }

    /**
     * Constructor
     * 
     * @param id
     * @param name
     * @param ticker
     */
    public Stock(final String name, final String ticker) {
        this.name = name;
        this.ticker = ticker;
    }

    /**
     * Object to String
     */
    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + ", ticker=" + ticker + "]";
    }

    /** Gets And Sets */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

}
