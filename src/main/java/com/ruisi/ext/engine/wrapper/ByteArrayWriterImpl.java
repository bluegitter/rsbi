//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruisi.ext.engine.wrapper;

import com.ruisi.ext.engine.view.exception.ExtRuntimeException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ByteArrayWriterImpl implements ExtWriter {
    private static Log a = LogFactory.getLog(ByteArrayWriterImpl.class);
    private ByteArrayOutputStream b;

    public ByteArrayWriterImpl(ByteArrayOutputStream var1) {
        this.b = var1;
    }

    public Object getProxy() {
        return this.b;
    }

    public String printAll() {
        return new String(this.b.toByteArray());
    }

    public void println(String var1) {
        try {
            if (var1 != null) {
                this.b.write(var1.getBytes());
                this.b.write(10);
            }
        } catch (IOException var3) {
            a.error(var3);
            throw new ExtRuntimeException(var3);
        }
    }

    public void print(String var1) {
        try {
            if (var1 != null) {
                this.b.write(var1.getBytes());
            }
        } catch (IOException var3) {
            a.error(var3);
            throw new ExtRuntimeException(var3);
        }
    }

    public void close() {
        try {
            this.b.close();
        } catch (IOException var2) {
            a.error(var2);
            throw new ExtRuntimeException(var2);
        }
    }

    public void flush() {
        try {
            this.b.flush();
        } catch (IOException var2) {
            a.error(var2);
            throw new ExtRuntimeException(var2);
        }
    }

    public void print(double var1, String var3) {
        DecimalFormat var4 = new DecimalFormat(var3);
        this.print(var4.format(var1));
    }
}
