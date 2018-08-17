package com.ap.game.chess.exception;

import lombok.Builder;

import java.time.Instant;
import java.util.Date;

public class ErrorDetailsBody {
  private Date timestamp;
  private String message;

  public ErrorDetailsBody(String message) {
    super();
    this.timestamp = new Date(Instant.now().toEpochMilli());
    this.message = message;
  }
}