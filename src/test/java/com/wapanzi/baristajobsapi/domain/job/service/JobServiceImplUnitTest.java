package com.wapanzi.baristajobsapi.domain.job.service;

import com.wapanzi.baristajobsapi.domain.job.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = NONE)
public class JobServiceImplUnitTest {
    @Autowired
    private JobService service;
    @MockBean
    private JobRepository repository;
    @Test
    void testRemoveJob_whenSuccessful_return() {
        // given
        willDoNothing().given(repository).deleteById(anyLong());

        // when
        service.removeJob(1L);

        // then
        BDDMockito.then(repository).should(times(1)).deleteById(1L);
    }
}
