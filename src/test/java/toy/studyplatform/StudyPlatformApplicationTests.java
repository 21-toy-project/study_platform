package toy.studyplatform;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import org.junit.jupiter.api.Test;

import toy.studyplatform.config.TestConfig;

@SpringBootTest
@Import(TestConfig.class)
class StudyPlatformApplicationTests {
    @Test
    void contextLoads() {}
}
