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
package com.couchbase.travelsample.ui.controller;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.DefaultListModel;

import com.couchbase.travelsample.db.BookmarkDao;
import com.couchbase.travelsample.db.LocalStore;
import com.couchbase.travelsample.model.Hotel;
import com.couchbase.travelsample.ui.Nav;
import com.couchbase.travelsample.ui.view.HotelSearchView;


@Singleton
public final class GuestController extends BaseController {
    private final static Logger LOGGER = Logger.getLogger(GuestController.class.getName());

    private final BookmarkDao bookmarkDao;

    private final DefaultListModel<Hotel> bookmarkListModel = new DefaultListModel<>();

    private boolean isFetchingBookmarks;

    @Inject
    public GuestController(Nav nav, LocalStore localStore, BookmarkDao bookmarkDao) {
        super(nav, localStore);
        this.bookmarkDao = bookmarkDao;
    }

    public DefaultListModel<Hotel> getBookmarksModel() { return bookmarkListModel; }

    public void selectHotel() { nav.toPage(HotelSearchView.PAGE_NAME); }

    public void fetchBookmarks() {
        if (isFetchingBookmarks) { return; }
        isFetchingBookmarks = true;
        bookmarkDao.getBookmarks(this::updateBookmarks);
    }

    public void bookmarkHotels(Set<Hotel> hotels) {
        bookmarkDao.addBookmarks(hotels);
        fetchBookmarks();
    }

    public void deleteBookmark(Hotel hotel) {
        bookmarkListModel.removeElement(hotel);
        bookmarkDao.removeBookmark(hotel);
    }

    private void updateBookmarks(List<Hotel> hotels) {
        bookmarkListModel.clear();
        for (Hotel hotel : hotels) { bookmarkListModel.addElement(hotel); }
        isFetchingBookmarks = false;
    }
}
