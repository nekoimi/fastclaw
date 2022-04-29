package com.nekoimi.fastclaw;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

/**
 * nekoimi  2022/2/17 11:35
 */
@SpringBootTest
public class FastCodeGeneratorTests {
    private final static String OUTPUT_DIR = "C:\\Users\\nekoimi\\Developer\\java_projects\\Nekoimi\\vasashi\\src\\main\\java";
    @Autowired
    private DataSource dataSource;

    @Test
    void testPublicSchemaGenerator() {
//        FastCodeGenerator.of().outputDir(OUTPUT_DIR).dataSource(dataSource)
//                .packageName("com.nekoimi.vasashi")
//                .schemaName("public")
//                .tablePrefix("sys_")
//                .tableName(List.of(
//                        "sys_user",
//                        "sys_user_info"
//                ))
//                .templateConsumer(builder -> builder
//                        .disable(TemplateType.ENTITY)
//                        .disable(TemplateType.SERVICE)
//                        .disable(TemplateType.SERVICEIMPL)
//                        .disable(TemplateType.CONTROLLER)
//                        .disable(TemplateType.MAPPER)
//                        .disable(TemplateType.XML)
//                )
//                .build()
//                .execute();
    }

    @Test
    void testLibrarySchemaGenerator() {
//        FastCodeGenerator.of().outputDir(OUTPUT_DIR).dataSource(dataSource)
//                .packageName("com.nekoimi.vasashi")
//                .schemaName("library")
//                .tablePrefix("")
//                .tableName(List.of("book"))
//                .build()
//                .execute();

//        FastCodeGenerator.of().outputDir(OUTPUT_DIR).dataSource(dataSource)
//                .packageName("com.nekoimi.vasashi")
//                .schemaName("library")
//                .tablePrefix("book_")
//                .tableName(List.of(
//                        "book_actress",
//                        "book_series",
//                        "book_image",
//                        "book_magnet",
//                        "book_torrent",
//                        "book_label",
//                        "book_label_rel"
//                ))
//                .build()
//                .execute();
    }
}
