package com.cuentas_movimiento.cuentas_movimiento.model.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clienteid", nullable = false)
    private Long clienteid;

    @Column(name = "contrasena", length = 100)
    private String contrasena;

    @Column(name = "estado", length = 20)
    private String estado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identificacion")
    private Person person;

}