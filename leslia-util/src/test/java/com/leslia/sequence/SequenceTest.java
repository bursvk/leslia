package com.leslia.sequence;

import com.leslia.util.sequence.Sequence;
import org.junit.Test;

public class SequenceTest {


    @Test
    public void test(){
        Sequence idWorker1 = new Sequence(0, 0);
        System.out.println(idWorker1.nextId());
    }


}
