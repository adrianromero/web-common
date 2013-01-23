//    Web Common is a utility library for web applications.
//    Copyright (C) 2012 Adrián Romero Corchado.
//
//    This file is part of Web Common
//
//     Licensed under the Apache License, Version 2.0 (the "License");
//     you may not use this file except in compliance with the License.
//     You may obtain a copy of the License at
//     
//         http://www.apache.org/licenses/LICENSE-2.0
//     
//     Unless required by applicable law or agreed to in writing, software
//     distributed under the License is distributed on an "AS IS" BASIS,
//     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//     See the License for the specific language governing permissions and
//     limitations under the License.

package com.adr.web.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import javax.servlet.ServletContext;

public class StringUtils {

    /** Creates a new instance of StringUtils */
    private StringUtils() {
    }
    
    public static String readServletResource(ServletContext c, String resource) throws IOException {
        
        InputStream in = c.getResourceAsStream(resource); // /WEB-INF/resources..
        if (in == null) {
            throw new FileNotFoundException(resource);
        }
        
        try {
            return readInputStream(in);
        } finally {
            in.close();
        }
    }
    
    public static String readResource(String resource) throws IOException {
        
        InputStream in = StringUtils.class.getResourceAsStream(resource);
        if (in == null) {
            throw new FileNotFoundException(resource);
        }

        try {
            return readInputStream(in);
        } finally {
            in.close();
        }
    }

    public static String readInputStream(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        byte[] data = out.toByteArray();

        return new String(data, "UTF-8");
    }

    public static String readReader(Reader r) throws IOException {

        StringBuilder out = new StringBuilder();
        char[] buffer = new char[1024];
        int len;
        while ((len = r.read(buffer)) > 0) {
            out.append(buffer, 0, len);
        }
        return out.toString();
    }
}
