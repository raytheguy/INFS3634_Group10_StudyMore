package com.example.studymore.ui.FlashCards;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class FlashCards{

    //maybe change cardId to UUID to reduce chance of conflict
    //now stores UUID as a String so Room can support it
    @PrimaryKey
    @NonNull
    String cardId;

    String front;
    String back;

    public String getCardId() {
        return cardId;
    }

    public FlashCards(String cardId, String front, String back) {
        this.cardId = cardId;
        this.front = front;
        this.back = back;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }
}
