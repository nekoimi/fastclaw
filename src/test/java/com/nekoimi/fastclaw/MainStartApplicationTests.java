package com.nekoimi.fastclaw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainStartApplicationTests {

    @Test
    void testHello() {
        Assertions.assertEquals("hello world", "hello world");
    }
}
