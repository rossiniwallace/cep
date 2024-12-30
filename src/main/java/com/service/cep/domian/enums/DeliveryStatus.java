package com.service.cep.domian.enums;

public enum DeliveryStatus {
    PENDING,        // Pedido aguardando processamento
    IN_TRANSIT,     // Em trânsito para o destino
    DELIVERED,      // Entregue
    CANCELLED;      // Cancelada

    public boolean isCompleted() {
        return this == DELIVERED || this == CANCELLED;
    }
}
