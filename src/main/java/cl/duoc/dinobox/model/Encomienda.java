package cl.duoc.dinobox.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "encomiendas")
public class Encomienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigo;

    private String destinatario;
    private String direccion;
    private String estado;
    
    @Column(length = 1000)
    private String historial; 

    public Encomienda() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getHistorial() { return historial; }
    public void setHistorial(String historial) { this.historial = historial; }
}