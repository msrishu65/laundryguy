package com.laundryguy.account.token.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
public class TokenService {
  private static final Logger logger = LogManager.getLogger(TokenService.class);


  public String generateRandomToken() {
      logger.info("generating random token id");
      SecureRandom random = new SecureRandom();
      return new BigInteger(130, random).toString(32);
  }


}
