// src/main/java/org/afs/pakinglot/domain/model/Ticket.java
package org.afs.pakinglot.domain.model;

public record Ticket(String plateNumber, int position, int parkingLot) {
}