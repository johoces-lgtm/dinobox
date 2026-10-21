package cl.duoc.dinobox.service;

import cl.duoc.dinobox.model.Encomienda;
import cl.duoc.dinobox.repository.EncomiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.List;

@Service
public class EncomiendaService {

    @Autowired
    private EncomiendaRepository repository;

    public Encomienda registrar(String destinatario, String direccion) {

        Encomienda enc = new Encomienda();

        Random random = new Random();
        String codigo = "DINO" + (10000 + random.nextInt(90000));

        while (repository.findByCodigo(codigo).isPresent()) {
            codigo = "DINO" + (10000 + random.nextInt(90000));
        }

        enc.setCodigo(codigo);
        enc.setDestinatario(destinatario);
        enc.setDireccion(direccion);
        enc.setEstado("En Recepción");

        enc.setHistorial(
                LocalDate.now() + " - En Recepción (" + direccion + ")");

        return repository.save(enc);
    }

    public Optional<Encomienda> buscarPorCodigo(String codigo) {
        return repository.findByCodigo(codigo.toUpperCase());
    }

    public Encomienda actualizarEstado(String codigo, String nuevoEstado, String ubicacion) {
        Optional<Encomienda> opt = repository.findByCodigo(codigo.toUpperCase());
        if (opt.isPresent()) {
            Encomienda enc = opt.get();
            enc.setEstado(nuevoEstado);
            String nuevoHistorial = enc.getHistorial() + " | " + LocalDate.now() + " - " + nuevoEstado + " ("
                    + ubicacion + ")";
            enc.setHistorial(nuevoHistorial);
            return repository.save(enc);
        }
        return null;
    }
    public List<Encomienda> listarTodas() {
        return repository.findAll();
    }

    public boolean eliminar(String codigo) {
        Optional<Encomienda> encomienda =
                repository.findByCodigo(codigo.toUpperCase());

        if (encomienda.isPresent()) {
            repository.delete(encomienda.get());
            return true;
        }

        return false;
    }
}