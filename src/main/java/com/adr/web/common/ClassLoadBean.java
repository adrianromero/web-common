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

package com.adr.web.common;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adrian
 */
public class ClassLoadBean<T> extends SingletonBean<T> {

    private static final Logger logger = Logger.getLogger(ClassLoadBean.class.getName());
    private Class<? extends T> clazz;

    public ClassLoadBean(String clazzname) {
        try {
            this.clazz = (Class<? extends T>) Class.forName(clazzname);
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    public ClassLoadBean(Class<? extends T> clazz) {
        this.clazz = clazz;
    }

    @Override
    protected T create() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            logger.log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
}
