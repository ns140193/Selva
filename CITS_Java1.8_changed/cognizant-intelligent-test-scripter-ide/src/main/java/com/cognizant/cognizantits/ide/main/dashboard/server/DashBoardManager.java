/*
 * Copyright 2014 - 2017 Cognizant Technology Solutions
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cognizant.cognizantits.ide.main.dashboard.server;

import com.cognizant.cognizantits.ide.main.mainui.AppMainFrame;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class DashBoardManager {

    public DashBoardServer server;

    AppMainFrame sMainFrame;

    public DashBoardManager(AppMainFrame sMainFrame) {
        this.sMainFrame = sMainFrame;
    }

    public DashBoardServer server() {
        if (server == null || !server.isAlive()) {
            server = new DashBoardServer();
            server.prepare();
            server.start();
        }
        return server;
    }

    public void stopServer() {
        if (server != null && server.isAlive()) {
            server.stopServer();
        }
    }

    public void onProjectChanged() {
        try {
            DashBoardData.setProjLocation(sMainFrame.getProject().getLocation());
            HarCompareHandler.init();
        } catch (Exception ex) {
            Logger.getLogger(DashBoardManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void openHarComapareInBrowser() {
        String add = server().url() + "/dashboard/harCompare/home.html";
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URL(add).toURI());
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(DashBoardManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
}
