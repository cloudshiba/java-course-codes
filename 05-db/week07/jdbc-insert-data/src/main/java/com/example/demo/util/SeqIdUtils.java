package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SeqIdUtils {
    private static final ThreadLocal<SimpleDateFormat> sdfLocal = new ThreadLocal<SimpleDateFormat>();
    private static final int SEQNO_MAX = 999999;
    private static int seqNo = 0;

    public static final String SEQ_FORMAT = "yyyyMMddHHmmss";

    public static String genUniqueKey(String className) throws Exception {
        int seqno;
        synchronized (className) {
            seqNo++;
            if (seqNo > SEQNO_MAX) {
                seqNo = 0;
            }
            seqno = seqNo;
        }
        StringBuilder seqSB = new StringBuilder("00000").append(seqno);
        return getNow() + seqSB.substring(seqSB.length() - 6);
    }

    private static String getNow() {
        SimpleDateFormat sdf = getSDFLocal();
        sdf.applyPattern(SEQ_FORMAT);
        return sdf.format(new Date());
    }

    private static final SimpleDateFormat getSDFLocal() {
        if (sdfLocal.get() == null) {
            sdfLocal.set(new SimpleDateFormat());
        }
        return sdfLocal.get();
    }
}
