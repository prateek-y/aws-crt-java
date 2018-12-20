/*
 * Copyright 2010-2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package software.amazon.awssdk.crt.io;

import software.amazon.awssdk.crt.CrtRuntimeException;
import software.amazon.awssdk.crt.CrtResource;

import java.io.Closeable;

/**
 * This class wraps the aws_event_loop_group from aws-c-io to provide
 * access to an event loop for the MQTT protocol stack in the AWS Common
 * Runtime.
 */
public final class EventLoopGroup extends CrtResource implements Closeable {

    public EventLoopGroup(int numThreads) throws CrtRuntimeException {
        acquire(eventLoopGroupNew(numThreads));
    }

    @Override
    public void close() {
        if (native_ptr() != 0) {
            eventLoopGroupDestroy(release());
        }
    }

    /*******************************************************************************
     * native methods
     ******************************************************************************/
    private static native long eventLoopGroupNew(int numThreads) throws CrtRuntimeException;
    private static native void eventLoopGroupDestroy(long elg);
};