package taekwon.springaop.aop.exam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taekwon.springaop.aop.exam.annotation.Trace;

import java.lang.annotation.Target;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository repository;

    @Trace
    public void request(String itemId) {
        repository.save(itemId);
    }
}
