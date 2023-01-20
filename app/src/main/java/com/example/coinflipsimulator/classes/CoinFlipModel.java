package com.example.coinflipsimulator.classes;

import java.util.Random;

public class CoinFlipModel {
  private static final Random rand = new Random();

  private boolean heads;

  public void flipCoin() {
    heads = rand.nextBoolean();
  }

  public boolean isHeads() {
    return heads;
  }
}
