package com.ap.game.chess.exception;

import lombok.Builder;

import java.time.Instant;
import java.util.Date;

@Builder
public class ErrorDetailsBody {
  private Date timestamp;
  private String message;
  private String details;

  public ErrorDetailsBody(Date timestamp, String message, String details) {
    super();
    this.timestamp = new Date(Instant.now().toEpochMilli());
    this.message = message;
  }
}