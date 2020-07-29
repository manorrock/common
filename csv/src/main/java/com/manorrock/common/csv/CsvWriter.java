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
package com.manorrock.common.csv;

import java.io.IOException;
import java.io.Writer;

/**
 * The CsvWriter class.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class CsvWriter extends Writer {
    
    /**
     * Stores the underlying writer.
     */
    private final Writer writer;
    
    /**
     * Constructor.
     * 
     * @param writer the underlying writer.
     */
    public CsvWriter(Writer writer) {
        this.writer = writer;
    }

    /**
     * Close the writer.
     * 
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void close() throws IOException {
        writer.close();
    }

    /**
     * Flush the writer.
     * 
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    /**
     * Write to the underlying writer.
     * 
     * @param buffer the character buffer.
     * @param offset the offset.
     * @param length the length.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void write(char[] buffer, int offset, int length) throws IOException {
        writer.write(buffer, offset, length);
    }
    
    /**
     * Write the object.
     * 
     * @param object the object.
     * @throws IOException when an I/O error occurs.
     */
    public void writeObject(Object object) throws IOException {
    }
}
