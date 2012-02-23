package org.jenkinsci.plugins.openjdk_native;

import hudson.console.LineTransformationOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class OpenJDKConsoleAnnotator extends LineTransformationOutputStream{

    private final OutputStream out;
    
    public OpenJDKConsoleAnnotator(OutputStream out) {
        this.out = out;
    }
    
    @Override
    protected void eol(byte[] b, int len) throws IOException {
        String line = Charset.defaultCharset().decode(ByteBuffer.wrap(b, 0, len)).toString();
        if (line.startsWith("[OpenJDK"))
            // put the annotation
            new OpenJDKErrorNote().encodeTo(out);
        out.write(b,0,len);
    }

    @Override
    public void close() throws IOException {
        super.close();
        out.close();
    }
    
}
