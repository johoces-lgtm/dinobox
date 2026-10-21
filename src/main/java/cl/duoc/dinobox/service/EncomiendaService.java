package cl.duoc.dinobox.service;

import cl.duoc.dinobox.model.Encomienda;
import cl.duoc.dinobox.repository.EncomiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EncomiendaService {

    @Autowired
    private EncomiendaRepository repository;

    public Encomienda registrar(String destinatario, String direccion) {
        Encomienda enc = new Encomienda();
        enc.setCodigo("TEMP_CODE");
        enc.setDestinatario(destinatario != null ? destinatario : "Usuario");
        enc.setDireccion(direccion != null ? direccion : "Santiago Centro");
        enc.setEstado("En Recepción");
        enc.setHistorial(LocalDate.now() + " - En Recepción (" + (direccion != null ? direccion : "Santiago Centro") + ")");

        Encomienda guardada = repository.save(enc);

        String codigoGenerado = "DINO" + (99 + guardada.getId());
        guardada.setCodigo(codigoGenerado);

        return repository.save(guardada);
    }

    public Optional<Encomienda> buscarPorCodigo(String codigo) {
        return repository.findByCodigo(codigo.toUpperCase());
    }

    public Encomienda actualizarEstado(String codigo, String nuevoEstado, String ubicacion) {
        Optional<Encomienda> opt = repository.findByCodigo(codigo.toUpperCase());
        if (opt.isPresent()) {
            Encomienda enc = opt.get();
            enc.setEstado(nuevoEstado);
            String nuevoHistorial = enc.getHistorial() + " | " + LocalDate.now() + " - " + nuevoEstado + " (" + ubicacion + ")";
            enc.setHistorial(nuevoHistorial);
            return repository.save(enc);
        }
        return null;
    }
}