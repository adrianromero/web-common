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

package com.adr.web.common.web;

import com.adr.web.common.AppContext;
import com.adr.web.common.AppContextConfig;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 * @author adrian
 */
public class HttpWebListener implements ServletContextListener {
    
    private static final Logger logger = Logger.getLogger(HttpWebListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        try {
            String clazzname = sce.getServletContext().getInitParameter("AppContextConfig");
            Class<? extends AppContextConfig> clazz = (Class<? extends AppContextConfig>) Class.forName(clazzname);
            
            AppContext.start();
            AppContext.getInstance().addBean(ServletContext.class, sce.getServletContext());
            
            clazz.newInstance().setup(AppContext.getInstance());
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
