//
// Copyright (c) 2019 Couchbase, Inc All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
package com.couchbase.travelsample.ui.view;

import java.awt.CardLayout;

import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class RootView extends JFrame {

    @Inject
    public RootView(LoginView loginView, GuestView guestView, HotelFlightView hotelFlightView) {
        super("Travel Sample");
        JPanel cards = new JPanel(new CardLayout());

        cards.add(loginView.getView());
        cards.add(guestView.getView());
        cards.add(hotelFlightView.getView());

        setContentPane(cards);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
