/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactive.scenarios;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author paul
 */
public class LoggingInputStream extends InputStream {

    private static final Logger log = LoggerFactory.getLogger(LoggingInputStream.class);

    private final InputStream delegate;

    public LoggingInputStream(InputStream stream) {
        this.delegate = stream;
        log.warn("[{}]", delegate.getClass().getName());
    }

    @Override
    public int read() throws IOException {
        int res = delegate.read();
        log.warn("Read 1 [{}]", res);
        return res;
    }

    @Override
    public int read(byte[] b) throws IOException {
        int res = delegate.read(b);
        log.warn("Read 2 [{}]", res);
        return res;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        log.warn("Being read [{}] [{}]", off, len);
        int res = delegate.read(b, off, len);
        log.warn("Returning [{}]", res);
        return res;
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        byte[] res = delegate.readAllBytes();
        log.warn("Read 3 [{}]", res);
        return res;
    }

    @Override
    public byte[] readNBytes(int len) throws IOException {
        byte[] res = delegate.readNBytes(len);
        log.warn("Read 4 [{}]", res);
        return res;
    }

    @Override
    public int readNBytes(byte[] b, int off, int len) throws IOException {
        int res = delegate.readNBytes(b, off, len);
        log.warn("Read 5 [{}]", res);
        return res;
    }

    @Override
    public long skip(long n) throws IOException {
        return delegate.skip(n);
    }

    @Override
    public void skipNBytes(long n) throws IOException {
        delegate.skipNBytes(n);
    }

    @Override
    public int available() throws IOException {
        int res = delegate.available();
        if (res == -1) {
            // This works around the issue
            //res = 0;
        }
        log.warn("Available [{}]", res);
        return res;
    }

    @Override
    public void close() throws IOException {
        delegate.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        delegate.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        delegate.reset();
    }

    @Override
    public boolean markSupported() {
        return delegate.markSupported();
    }

    @Override
    public long transferTo(OutputStream out) throws IOException {
        log.warn("Being read 10");
        return delegate.transferTo(out);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return delegate.equals(obj);
    }

    @Override
    public String toString() {
        return delegate.toString();
    }


}
