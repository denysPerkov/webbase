package filter.util;

import filter.util.ByteServletOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResponseWrapper extends HttpServletResponseWrapper {

    private boolean writer;
    private boolean stream;
    private final StringWriter stringWriter;
    private final ByteArrayOutputStream byteArrayOutputStream;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        byteArrayOutputStream = new ByteArrayOutputStream();
        stringWriter = new StringWriter();
    }

    @Override
    public PrintWriter getWriter() {
        if (stream)
            throw new IllegalStateException();
        writer = true;
        return (new PrintWriter(stringWriter));
    }

    @Override
    public ServletOutputStream getOutputStream() {
        if (writer) {
            throw new IllegalStateException();
        }
        stream = true;
        return new ByteServletOutputStream(byteArrayOutputStream);
    }

    @Override
    public String toString() {
        return (stringWriter.toString());
    }

    @Override
    public void setContentLength(int len) {
    }

    public byte[] getBytes() {
        return byteArrayOutputStream.toByteArray();
    }

    public boolean isWriter() {
        return writer;
    }

    public boolean isStream() {
        return stream;
    }
}