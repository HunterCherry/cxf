/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cxf.rs.security.jose.jwe;

import java.security.spec.AlgorithmParameterSpec;

import org.apache.cxf.common.util.crypto.CryptoUtils;


public abstract class AbstractContentEncryptionCipherProperties implements ContentEncryptionCipherProperties {
    private static final int DEFAULT_AUTH_TAG_LENGTH = 128;
    private int authTagLen = DEFAULT_AUTH_TAG_LENGTH;
    private String algo;
    public AbstractContentEncryptionCipherProperties(String algo) {
        this.algo = algo;
    }
    public AlgorithmParameterSpec getAlgorithmParameterSpec(byte[] theIv) {
        return CryptoUtils.getContentEncryptionCipherSpec(getAuthTagLen(), theIv);
    }
    public byte[] getAdditionalAuthenticationData(String headersJson) {
        return JweHeaders.toCipherAdditionalAuthData(headersJson);
    }
    protected int getAuthTagLen() {
        return authTagLen;
    }
    @Override
    public String getAlgorithm() {
        return algo;    
    }
}
