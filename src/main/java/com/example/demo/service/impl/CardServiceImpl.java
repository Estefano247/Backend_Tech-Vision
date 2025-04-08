package com.example.demo.service.impl;

import com.example.demo.entity.Card;
import com.example.demo.entity.ProviderCard;
import com.example.demo.entity.UserCard;
import com.example.demo.repository.CardRepository;
import com.example.demo.service.CardService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Random;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    private String generateValidCardNumber() {
        Random random = new Random();
        int[] digits = new int[16];
    
        // Generar los primeros 15 dígitos aleatoriamente
        for (int i = 0; i < 15; i++) {
            digits[i] = random.nextInt(10);
        }
    
        // Calcular el dígito verificador (Luhn)
        int sum = 0;
        for (int i = 0; i < 15; i++) {
            int digit = digits[14 - i];
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }
            sum += digit;
        }
    
        digits[15] = (10 - (sum % 10)) % 10;
    
        // Convertir a número largo
        StringBuilder sb = new StringBuilder();
        for (int digit : digits) {
            sb.append(digit);
        }
    
        return sb.toString();
    }
    
    @Override
    @Transactional
    public Card createCard(Card card) {
        // Generar un número de tarjeta válido
        String panString = generateValidCardNumber();
        card.setPan(Long.parseUnsignedLong(panString));
        card.setBalance(0.0);
        card.setTimeCreation(new Timestamp(System.currentTimeMillis()));
        return cardRepository.save(card);
    }

@Override
@Transactional
public boolean deleteCard(Long pan) {
    Optional<Card> optionalCard = cardRepository.findById(pan);
    
    if (optionalCard.isPresent()) {
        Card card = optionalCard.get();

        // Si es una tarjeta de proveedor
        if (card instanceof ProviderCard providerCard && providerCard.getProvider() != null) {
            providerCard.getProvider().setCards(null);
        }

        // Si es una tarjeta de usuario (si tienes algo similar)
        if (card instanceof UserCard userCard && userCard.getUser() != null) {
            userCard.getUser().setCard(null);
        }

        cardRepository.delete(card);
        return true;
    }

    return false;
}

}    
