package com.example.demo.controller;

import com.example.demo.entity.Card;
import com.example.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<Card> crearTarjeta(@RequestBody Card card) {
        try {
            Card tarjetaCreada = cardService.createCard(card);
            return ResponseEntity.ok(tarjetaCreada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{pan}")
    public Map<String, String> deleteCard(@PathVariable Long pan) {
        boolean deleted = cardService.deleteCard(pan);

        if (deleted) {
            return Map.of("message", "Tarjeta eliminada");
        } else {
            return Map.of("error", "Tarjeta no encontrada");
        }
    }
}
