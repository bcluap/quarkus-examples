/*
 * Copyright (c) 2016, Uber Technologies, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package reactive.scenarios;

import io.jaegertracing.internal.JaegerSpan;
import io.jaegertracing.internal.exceptions.SenderException;
import io.jaegertracing.spi.Sender;
import io.smallrye.common.constraint.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingSender implements Sender {

    private static final Logger log = LoggerFactory.getLogger(LoggingSender.class);

    @Override
    public int close() throws SenderException {
        return 0;
    }

    @Override
    public int append(JaegerSpan span) throws SenderException {
        log.info("Received span to send [{}] with Tags [{}]", span, span.getTags());
        assertTrue(span.getOperationName().contains("POST:reactive.scenarios.TestSubResource.processPost"), "Servicename is " + span.getOperationName() + " instead of POST:reactive.scenarios.TestSubResource.processPost");
        assertTrue(span.getTags().containsKey("filterInTestTag"), "No tag filterInTestTag found");
        assertTrue(span.getTags().containsKey("filterOutTestTag"), "No tag filterOutTestTag found");
        assertTrue(span.getTags().containsKey("aroundReadFromTestTag"), "No tag aroundReadFromTestTag found");
        assertTrue(span.getTags().containsKey("aroundWriteToTestTag"), "No tag aroundWriteToTestTag found");
        return 0;
    }

    @Override
    public int flush() throws SenderException {
        return 0;
    }

    private void assertTrue(boolean assertion, String msg) {
        if (!assertion) {
            log.error("\n\nAssertion failed!!!!!: {}\n", msg);
        }
    }

}
