package com.kazakov.eventkeeper.mainservice.model;

import lombok.*;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "requests")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @ToString.Exclude
    private Event event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", referencedColumnName = "id")
    @ToString.Exclude
    private User requester;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    @Column(name = "created_date")
    private LocalDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass()  != Hibernate.unproxy(o).getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
