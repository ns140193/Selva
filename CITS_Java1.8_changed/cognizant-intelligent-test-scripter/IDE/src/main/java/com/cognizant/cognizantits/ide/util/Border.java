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
package com.cognizant.cognizantits.ide.util;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;

/**
 *
 * 
 */
public class Border {

    public static  javax.swing.border.Border transBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255, 0)),
            focusBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#13BEFF")),
            selectedBorder = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.decode("#13BEFF")),
            thumbPrevOnFocus=new LineBorder(Color.GRAY, 3),
            thumbPrevOffFocus=new LineBorder(Color.LIGHT_GRAY, 3),
            thumbPrevSelected=new LineBorder(Color.DARK_GRAY, 3);
    
}
