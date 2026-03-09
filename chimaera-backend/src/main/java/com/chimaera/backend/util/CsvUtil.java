package com.chimaera.backend.util;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Collections;
import java.util.List;

public class CsvUtil {

    private CsvUtil() {
        // Utility class, no instantiation required
    }

    public static <T> List<T> parseCsv(Reader reader, Class<T> type) {
        try {
            return new CsvToBeanBuilder<T>(reader)
                    .withType(type)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
