/*
 *  Copyright (c) 2002-2020, Manorrock.com. All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *      1. Redistributions of source code must retain the above copyright
 *         notice, this list of conditions and the following disclaimer.
 *
 *      2. Redistributions in binary form must reproduce the above copyright
 *         notice, this list of conditions and the following disclaimer in the
 *         documentation and/or other materials provided with the distribution.
 *
 *      3. Neither the name of the copyright holder nor the names of its 
 *         contributors may be used to endorse or promote products derived from
 *         this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY OF SUCH DAMAGE.
 */
package com.manorrock.common.yaml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static java.util.logging.Level.WARNING;
import java.util.logging.Logger;

/**
 * The default Yaml encoder.
 *
 * <p>
 * This Yaml encoder uses reflection to determine what needs to be encoded.
 * </p>
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class DefaultYamlEncoder implements YamlEncoder {
    
    /**
     * Stores the logger.
     */
    private static final Logger LOGGER = Logger.getLogger(DefaultYamlEncoder.class.getPackage().getName());

    /**
     * @see YamlEncoder#encode(java.lang.Object)
     */
    @Override
    public String encode(Object object) {
        return encode(object, 0);
    }

    /**
     * Private method used for actual encoding.
     *
     * @param object the object.
     * @param indentation the indentation.
     */
    private String encode(Object object, int indentation) {
        StringBuilder result = new StringBuilder();
        String indent = getIndentation(indentation);
        Method[] methods = object.getClass().getMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("get")
                        || method.getName().startsWith("is")) {
                    if (method.getReturnType().isPrimitive()) {
                        result
                                .append(indent)
                                .append(getPropertyName(method))
                                .append(": ")
                                .append(method.invoke(object, new Object[]{}))
                                .append("\n");
                    }
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                if (LOGGER.isLoggable(WARNING)) {
                    LOGGER.log(WARNING, "An error occurred during encoding:", ex);
                }
            }
        }
        return result.toString();
    }

    /**
     * Get the property name.
     *
     * @param method the method.
     * @return the property name.
     */
    private String getPropertyName(Method method) {
        String propertyName = null;
        if (method.getName().startsWith("get")) {
            propertyName = Character.toLowerCase(method.getName().charAt(3))
                    + method.getName().substring(4);
        }
        if (method.getName().startsWith("is")) {
            propertyName = Character.toLowerCase(method.getName().charAt(2))
                    + method.getName().substring(3);
        }
        return propertyName;
    }

    /**
     * Get the indentation.
     * 
     * @param indentation
     * @return 
     */
    private String getIndentation(int indentation) {
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<indentation; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }
}
