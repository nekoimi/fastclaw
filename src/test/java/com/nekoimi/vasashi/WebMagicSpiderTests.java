package com.nekoimi.vasashi;

import com.nekoimi.vasashi.framework.webmagic.runner.WebMagicRunner;
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

    @Test
    void runTest1() {
        seHuaTangRunner.start();
    }
}
