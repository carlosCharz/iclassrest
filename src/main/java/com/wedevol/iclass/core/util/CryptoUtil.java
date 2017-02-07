package com.wedevol.iclass.core.util;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.RandomBasedGenerator;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

import java.util.UUID;

/**
 * Crypto Util Class
 * 
 * @author charz
 * 
 */
public class CryptoUtil {

    private static TimeBasedGenerator TB_GENERATOR = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
    private static RandomBasedGenerator RD_GENERATOR = Generators.randomBasedGenerator();

    public static UUID getRandomBasedUUID() {
        return RD_GENERATOR.generate();
    }

    public static String getRandomBasedUUIDString() {
        return getRandomBasedUUID().toString().replace("-","");
    }

    public static UUID getTimeBasedUUID() {
        return TB_GENERATOR.generate();
    }

    public static String getTimeBasedUUIDString() {
        return getTimeBasedUUID().toString().replace("-","");
    }

}
