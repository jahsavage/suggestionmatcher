AEVI Test

A coding test where you compose a list of suggestions in one app/activity and try and guess an item on the list in another activity.

Assumptions

1) The code for both half's of this test are in the same project for convenience but to truly prove a call from another app should be in separate projects.
2) The suggestion list is expressed as a comma separated list.
3) Matching suggestions are displayed in a drop down list


High Level Description

The project is architected using dagger and MVP techniques to enable separation of business rules and logic from the UI processing. This approach will permit easy expansion and provide a firm background for any future development
Two activities are used to model the problem, one for composing the suggestion list and another for guessing an item on the suggestion list. A small message window on the composer screen is used to feedback to the user and indicate selections from the matching window. 
By default the Composer activity will show first but by moddifying the Android Manifest the Suggestion Matcher window can be run in isolation, this will currently present the static data in the design documentation. Default suggestion data is accessed via the SuggestionInteractor interface which is expresed using RxJava syntax. The implmentation can be changed to plug in external data sources.


To Run

Open in Android Studio and launch app, follow on screen instructions
 

To Test

Run either the unit tests or the integration tests

Tools Used

Dagger for architecture and component assignment
Rxjava to permit easy plugin to what ever source of suggestion mechanism is required
Retrofit for above
Timber for logging
Butterknife for UI mapping
Junit for testing
Mockito for testing
Espresso for integration tests



