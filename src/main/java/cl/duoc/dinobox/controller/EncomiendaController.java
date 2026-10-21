package cl.duoc.dinobox.controller;

import cl.duoc.dinobox.model.Encomienda;
import cl.duoc.dinobox.service.EncomiendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/encomienda")
@CrossOrigin(origins = "*")
@Tag(name = "Encomiendas DinoBox", description = "API con Spring Data JPA y Base de Datos")
public class EncomiendaController {

    @Autowired
    private EncomiendaService service;

    @PostMapping
    @Operation(summary = "Registrar nueva encomienda")
    public ResponseEntity<Map<String, Object>> registrar(@RequestBody Map<String, String> body) {

        String destinatario = body.get("destinatario");
        String direccion = body.get("direccion");

        if (destinatario == null || destinatario.trim().isEmpty()
                || direccion == null || direccion.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Debes completar todos los campos"));
        }

        Encomienda enc = service.registrar(destinatario.trim(), direccion.trim());
        return ResponseEntity.ok(Map.of(
                "mensaje", "Encomienda registrada con éxito en Base de Datos",
                "codigoSeguimiento", enc.getCodigo()));
    }

    @GetMapping("/{codigo}")
    @Operation(summary = "Obtener encomienda por código")
    public ResponseEntity<?> obtener(@PathVariable String codigo) {
        Optional<Encomienda> enc = service.buscarPorCodigo(codigo);
        if (enc.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("error", "Encomienda no encontrada"));
        }
        return ResponseEntity.ok(enc.get());
    }

    @PutMapping("/{codigo}")
    @Operation(summary = "Actualizar estado de la encomienda")
    public ResponseEntity<?> actualizar(@PathVariable String codigo, @RequestBody Map<String, String> body) {
        Encomienda enc = service.actualizarEstado(codigo, body.get("estado"), body.get("ubicacion"));
        if (enc == null) {
            return ResponseEntity.status(404).body(Map.of("error", "Encomienda no encontrada"));
        }
        return ResponseEntity.ok(Map.of(
                "mensaje", "Estado actualizado correctamente",
                "encomienda", enc));
    }

    @GetMapping
    @Operation(summary = "Listar todas las encomiendas")
    public List<Encomienda> listar() {
        return service.listarTodas();
    }

    @DeleteMapping("/{codigo}")
    @Operation(summary = "Eliminar encomienda por código")
    public ResponseEntity<?> eliminar(@PathVariable String codigo) {

        boolean eliminada = service.eliminar(codigo);

        if (eliminada) {
            return ResponseEntity.ok(
                    Map.of("mensaje", "Encomienda eliminada correctamente"));
        }

        return ResponseEntity.status(404).body(
                Map.of("error", "Encomienda no encontrada"));
    }
}