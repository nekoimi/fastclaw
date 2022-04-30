package com.nekoimi.fastclaw;

import com.nekoimi.fastclaw.framework.webmagic.runner.WebMagicRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>WebMagicSpiderTests</p>
 *
 * @author nekoimi 2022/4/27
 */
@SpringBootTest
public class WebMagicSpiderTests {

    @Qualifier("seHuaTangRunner")
    @Autowired
    private WebMagicRunner seHuaTangRunner;

    @Qualifier("javBooksRunner")
    @Autowired
    private WebMagicRunner javBooksRunner;

    @Test
    void runTest1() {
        seHuaTangRunner.start();
    }

    @Test
    void runTests2() {
        javBooksRunner.start();
    }
}
