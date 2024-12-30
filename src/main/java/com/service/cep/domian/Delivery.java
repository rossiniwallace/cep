package com.service.cep.domian;

import com.service.cep.domian.enums.DeliveryStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(name = "origin")
    private String origin;
    @Column(name = "destination")
    private String destination;
    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column(name = "tracking_code", unique = true)
    private String trackingCode;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public Delivery() {
    }

    public Delivery(Long id, String origin, String destination, Date orderDate, String trackingCode, DeliveryStatus status) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.orderDate = orderDate;
        this.trackingCode = trackingCode;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

}
