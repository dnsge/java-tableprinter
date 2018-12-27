package org.dnsge.util.tableprinter.row;

import org.dnsge.util.tableprinter.table.TableHeader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RowMappingProtocolTest {

    private RowMappingProtocol<Integer> rowMappingProtocol;

    @BeforeEach
    void setup() {
        rowMappingProtocol = new RowMappingProtocol<>(
                new TableHeader("First", "Second"),
                integer -> Arrays.asList(integer.toString(), integer + 10));
    }

    @Test
    void apply() {
        assertEquals(List.of("5", "15"), rowMappingProtocol.apply(5));
        assertEquals(List.of("100", "110"), rowMappingProtocol.apply(100));
    }

    @Test
    void getTableHeader() {
        assertEquals(new TableHeader("First", "Second"), rowMappingProtocol.getTableHeader());
    }
}
