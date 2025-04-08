package com.example.demo.service;

import com.example.demo.entity.Card;

public interface CardService {
    Card createCard(Card card);
    boolean deleteCard(Long pan);

}
