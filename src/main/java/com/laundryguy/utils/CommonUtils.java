package com.laundryguy.utils;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by maninder on 9/7/16.
 */
public class CommonUtils {

    private static final Random fRandom = new Random(9200222L);

    public static String generateOrderID() {

        // changed by LSA Software to generate 16 digits id, based on
        // currentTime + a random number
        // add random number to our system time
        AtomicInteger rand = new AtomicInteger(fRandom.nextInt(999));
        AtomicReference<String> atomicReference = new AtomicReference<String>(String.valueOf(System.currentTimeMillis()) + rand.get());
        return Long.toHexString(Long.valueOf(atomicReference.get()));
    }

}
