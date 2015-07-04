# TodoCodePathApp

TodoCodePathApp is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item. Not only does it do todos but it is actually a super secret Cat spy software for the underground cat illuminati society.

Submitted by: Haider Khan (ZkHaider)

Time spent: 6 hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **successfully add and remove items** from the todo list
* [X] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [X] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [X] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [X] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [ ] Add support for completion due dates for todo items (and display within listview item)
* [X] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [ ] Add support for selecting the priority of each todo item (and display in listview item)
* [X] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [X] Super secret Caturday party viewpager utilizing The Cat API.
* [X] Use of Android's Palette Library for Vibrant Colors.
* [X] API Integration using Retrofit and SimpleXMLConverter.
* [X] Event-Bus system integration for better architecture.
* [X] TimeStamp Util class that displays time as "X minutes ago", "X hours ago", "X days ago", etc.

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

#### Floating Action Button Menu

<a href="http://i.giflike.com/embed/vck7Y0o"><img src="http://i.giflike.com/vck7Y0o.gif"/></a>

#### Todo Add Item

<a href="http://i.giflike.com/embed/M78w98m"><img src="http://i.giflike.com/M78w98m.gif"/></a>

<a href="http://i.giflike.com/embed/0gU18GQ"><img src="http://i.giflike.com/0gU18GQ.gif"/></a>

<a href="http://i.giflike.com/embed/uWYJTk0"><img src="http://i.giflike.com/uWYJTk0.gif"/></a>

#### Todo Delete Item

<a href="http://i.giflike.com/embed/Tl4An6W"><img src="http://i.giflike.com/Tl4An6W.gif"/></a>

<a href="http://i.giflike.com/embed/yhuVqpR"><img src="http://i.giflike.com/yhuVqpR.gif"/></a>

#### Todo Cancel Remove Swipe

<a href="http://i.giflike.com/embed/1sJOdtg"><img src="http://i.giflike.com/1sJOdtg.gif"/></a>

#### Todo Persistence 

<a href="http://i.giflike.com/embed/JKQhZRa"><img src="http://i.giflike.com/JKQhZRa.gif"/></a>

<a href="http://i.giflike.com/embed/nITdiVB"><img src="http://i.giflike.com/nITdiVB.gif"/></a>

#### Todo Caturday Secret Illuminati Society

<a href="http://i.giflike.com/embed/4i1aCb6"><img src="http://i.giflike.com/4i1aCb6.gif"/></a>

## Notes

The biggest challenge for this application was probably working with Cursors and SQLiteOpenHelper class, and persisting a Object Relational Map class. Those things probably took the longest amount of time to figure out. The next difficult thing was probably Fragment management, there is only one activity in this application and three different fragments that are managed.

## License

    Copyright 2015 ZkHaider

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
