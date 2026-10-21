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
        Encomienda enc = service.registrar(body.get("destinatario"), body.get("direccion"));
        return ResponseEntity.ok(Map.of(
                "mensaje", "Encomienda registrada con éxito en Base de Datos",
                "codigoSeguimiento", enc.getCodigo()
        ));
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
                "encomienda", enc
        ));
    }
}