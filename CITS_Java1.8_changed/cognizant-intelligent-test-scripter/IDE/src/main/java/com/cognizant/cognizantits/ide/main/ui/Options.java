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
package com.cognizant.cognizantits.ide.main.ui;

import com.cognizant.cognizantits.engine.constants.FilePath;
import com.cognizant.cognizantits.ide.settings.AppSettings;
import com.cognizant.cognizantits.ide.settings.AppSettings.APP_SETTINGS;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

/**
 *
 * 
 */
public class Options extends javax.swing.JDialog {

    private Boolean saved;

    private final List<String> themes = new ArrayList<>();
    private final Map<String, Object> previewMap = new HashMap();

    public Options() {
        initComponents();
        load();
    }

    private void load() {
        openRecent.setSelected(Boolean.valueOf(AppSettings.get(APP_SETTINGS.LOAD_RECENT.getKey())));
        addonPort.setValue(Integer.valueOf(AppSettings.get(APP_SETTINGS.ADDON_PORT.getKey())));
        harPort.setValue(Integer.valueOf(AppSettings.get(APP_SETTINGS.HAR_PORT.getKey())));
        defaultWaitTime.setValue(Integer.valueOf(AppSettings.get(APP_SETTINGS.DEFAULT_WAIT_TIME.getKey())));
        elementWaitTime.setValue(Integer.valueOf(AppSettings.get(APP_SETTINGS.ELEMENT_WAIT_TIME.getKey())));
        standaloneReport.setSelected(Boolean.valueOf(AppSettings.get(APP_SETTINGS.STANDALONE_REPORT.getKey())));
        loadReportThemes();
        saved = true;
    }

    private void loadReportThemes() {
        loadThemes();
        loadPreview();
        loadDefaultTheme();
    }

    private void loadThemes() {
        themes.clear();
        File themeFolder = new File(FilePath.getReportThemePath());
        if (themeFolder.exists()) {
            for (File file : themeFolder.listFiles()) {
                if (file.getName().endsWith(".css")) {
                    themes.add(file.getName().replace(".css", ""));
                }
            }
        }
        reportCombo.setModel(new DefaultComboBoxModel(themes.toArray()));
    }

    private void loadPreview() {
        previewMap.clear();
        File previewFolder = new File(FilePath.getReportThemePreviewPath());
        if (previewFolder.exists()) {
            for (File file : previewFolder.listFiles()) {
                String name = org.apache.commons.io.FilenameUtils.removeExtension(file.getName());
                if (themes.contains(name)) {
                    previewMap.put(name, file.getAbsolutePath());
                }
            }
        }

    }

    private void loadDefaultTheme() {
        if (!themes.isEmpty()) {
            String themedef = AppSettings.get(APP_SETTINGS.THEME.getKey());
            if (themedef.isEmpty() || !themes.contains(themedef)) {
                themedef = themes.get(0);
            }
            settheme(themedef);
        }
    }

    private void settheme(String themeName) {
        reportCombo.setSelectedItem(themeName);
        ImageIcon icon = null;
        try {
            icon = new ImageIcon((String) previewMap.get(themeName));
        } catch (Exception ex) {

        }
        if (icon == null) {
            icon = new ImageIcon(getClass().getResource("/ui/resources/pnf.png"));
        }
        int w = reportViewer.getWidth();
        int h = reportViewer.getHeight();
        Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        reportViewer.setIcon(new ImageIcon(img));
        reportViewer.setToolTipText("<html><body><img src='" + getUrlFromFile(previewMap.get(themeName)) + "'></body></html>");
    }

    private String getUrlFromFile(Object obj) {
        try {
            URI url = new File(obj.toString()).toURI();
            return url.toString();
        } catch (Exception ex) {
            return null;
        }
    }

    private void storeDefaultTheme() {
        AppSettings.set(APP_SETTINGS.THEME.getKey(), reportCombo.getSelectedItem().toString());
        AppSettings.set(APP_SETTINGS.THEMES.getKey(),
                themes.toString().replace("]", "").replace("[", "").replace(", ", ","));
        AppSettings.store("Theme Settings");

    }

    public void showOptions() {
        if (isVisible()) {
            toFront();
        } else {
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        addonPort = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        harPort = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        openRecent = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        defaultWaitTime = new javax.swing.JSpinner();
        elementWaitTime = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        reportCombo = new javax.swing.JComboBox<>();
        reportViewer = new javax.swing.JLabel();
        standaloneReport = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Options");
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Extension Port");

        addonPort.setModel(new javax.swing.SpinnerNumberModel(8887, 1024, 65536, 1));
        addonPort.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                addonPortStateChanged(evt);
            }
        });

        jLabel2.setText("Har Comparator Port");

        harPort.setModel(new javax.swing.SpinnerNumberModel(11234, 1024, 65536, 1));
        harPort.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                harPortStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addonPort, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(harPort, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(addonPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(harPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        openRecent.setText("Open last viewed project on startup");
        openRecent.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                openRecentItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(openRecent)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(openRecent)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Wait Time");

        jLabel4.setText("Element Wait Time");

        defaultWaitTime.setModel(new javax.swing.SpinnerNumberModel(0, 0, 300, 1));
        defaultWaitTime.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                defaultWaitTimeStateChanged(evt);
            }
        });

        elementWaitTime.setModel(new javax.swing.SpinnerNumberModel(0, 0, 300, 1));
        elementWaitTime.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                elementWaitTimeStateChanged(evt);
            }
        });

        jLabel5.setText("seconds");

        jLabel6.setText("seconds");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(defaultWaitTime, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(elementWaitTime, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(defaultWaitTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(elementWaitTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Reporting Theme");

        reportCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        reportCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                reportComboItemStateChanged(evt);
            }
        });

        reportViewer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reportViewer.setText("Sample");
        reportViewer.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        reportViewer.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        reportViewer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        standaloneReport.setText("Create Standalone Report");
        standaloneReport.setToolTipText("Pack all dependent files into execution result");
        standaloneReport.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                standaloneReportItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(reportViewer, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(standaloneReport)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(27, 27, 27)
                                .addComponent(reportCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(standaloneReport)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(reportCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(reportViewer, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (!saved) {
            AppSettings.store("Options Changed");
            storeDefaultTheme();
        }
    }//GEN-LAST:event_formWindowClosing

    private void openRecentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_openRecentItemStateChanged
        AppSettings.openRecentProjectsOnLaunch(evt.getStateChange() == ItemEvent.SELECTED);
        saved = false;
    }//GEN-LAST:event_openRecentItemStateChanged

    private void addonPortStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_addonPortStateChanged
        AppSettings.set(APP_SETTINGS.ADDON_PORT.getKey(), addonPort.getValue().toString());
        saved = false;
    }//GEN-LAST:event_addonPortStateChanged

    private void harPortStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_harPortStateChanged
        AppSettings.set(APP_SETTINGS.HAR_PORT.getKey(), harPort.getValue().toString());
        saved = false;
    }//GEN-LAST:event_harPortStateChanged

    private void defaultWaitTimeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_defaultWaitTimeStateChanged
        AppSettings.set(APP_SETTINGS.DEFAULT_WAIT_TIME.getKey(), defaultWaitTime.getValue().toString());
        saved = false;
    }//GEN-LAST:event_defaultWaitTimeStateChanged

    private void elementWaitTimeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_elementWaitTimeStateChanged
        AppSettings.set(APP_SETTINGS.ELEMENT_WAIT_TIME.getKey(), elementWaitTime.getValue().toString());
        saved = false;
    }//GEN-LAST:event_elementWaitTimeStateChanged

    private void reportComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_reportComboItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            settheme(evt.getItem().toString());
            saved = false;
        }
    }//GEN-LAST:event_reportComboItemStateChanged

    private void standaloneReportItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_standaloneReportItemStateChanged
        AppSettings.set(APP_SETTINGS.STANDALONE_REPORT.getKey(), String.valueOf(standaloneReport.isSelected()));
        saved = false;
    }//GEN-LAST:event_standaloneReportItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner addonPort;
    private javax.swing.JSpinner defaultWaitTime;
    private javax.swing.JSpinner elementWaitTime;
    private javax.swing.JSpinner harPort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JCheckBox openRecent;
    private javax.swing.JComboBox<String> reportCombo;
    private javax.swing.JLabel reportViewer;
    private javax.swing.JCheckBox standaloneReport;
    // End of variables declaration//GEN-END:variables
}
