# Android Interview Questions:

#### What is Android? 
Android is an Open Source mobile operating system created by Google. It is used to power a wide range of mobile phones, tablets, and laptops.

#### What is APK?
It is a file format, it means Android Application Package File and is used to distribute and install applications on Android.

#### What is the difference between Dalvik and ART? 
- Dalvik - JIT(just in time)compilation (compilation done during the execution of the application, at runtime) 
- ART (Android Runtime)- JIT and AOT(ahead of time) compilation, optimize garbage collector and improved debugging support (introduced in Android 5.0)

#### How do you track memory leaks? 
 - I’d profile my application to use the Memory Monitor section of the Android Monitor tool provided by Android Studio. 
 - From there I can begin to analyze the frequency and patterns of garbage collection, dump the Java heap to identify candidate objects that get or stay allocated unexpectedly or unnecessarily, and as well use the allocation tracking to detect where exactly in my code are the problems happening. With 2.

#### What are the ways you use to debug a program? 
1. first select the device to run the applicaton  
2. define my breakpoints
3. run the application with the debugger tool 
  - examine the variables at runtime 
  - use logcat for logged messages and system logs

#### How do you support multiple languages? 
 - with in the resources create an alternative strings.xml file with the desired language(s) locale qualifier.

#### What are 9-patch images? 
 - 9-Patch image is a bitmap images that automatically resize to adjust to the contents of the view and the size of the 
 - screen. It can be created in Android studio with the WYSIWYG editor

#### How can you guarantee backwards compatibility in Android? 
  - In the manifest define the lowest Android API level that the app is going to target
  - then create the resources accordingly, using the available Android supporting libraries, and programmatically address the implementation of supporting features by querying the current device OS API Level.

#### How do you support phones and tablets? 
  - create a UI design that is flexible and can translate well with different screen sizes and devices
  - create alternative resources files for layout and dimension values with qualifiers

## ACTIVITY 

#### What is an Activity? 
An Activity is an abstract class that is one of the 4 key component of Android:
- provides a screen that the user interacts with
- provides the UI elements 
- derived from the Context class
	
#### What is the Android lifecycle? 
It’s the cycle that every Activity in any Android application follows.
Activity Lifecycle:
	1. OnCreate() 
	2. OnStart()  
	3. OnResume()  
	4. OnPause()  
	5. OnStop() 
	6. OnDestory()  
	- OnRestore()  

#### What are the lifecycle callbacks that are guaranteed to happen in an Activity? 
The onCreate, onStart, onResume, onPause, onStop

#### Can I destroy an Activity? 
 - finish method  
 - finishActivity method - if the activity you want to destroy was started from the Activity you’re currently at

#### I want to get information back from an Activity, how can I do that? 
 - You would start the activity with the startActivityForResult and onActivityResult methods.

#### What is the difference between StartActivity() and StartActivityResult()?


#### How can I manage rotation changes? 
- I would access the System’s Window Service through a Window manager with the current context to get the current rotation and from there decide what to do accordingly.

- If I’m in Activity A and I enter Activity B, what are the callbacks triggered? Activity A will onPause then Activity B will onCreate, onStart, onResume and finally Activity A will onStop.

- If I’m in Activity A, then I enter Activity B and then press the back button, what are the callbacks triggered? Activity B will onPause, then Activity A will onRestart, onStart, and onResume. Finally Activity B will onStop and then onDestroy

#### How can I send information from an Activity to another Activity? 
You can send information through Activities using Extras through the various available putExtra methods that use key-value pairs. 
 - Extras with the put/getExtra() methods - uses key-value pair (primitives, parcelables, and bundles)
 - startActivityForResult and onActivityResult methods 
 - EventBus - third party library that simplifies the communication between components (Activities, Fragments, and background threads) 

#### When is the onDestroy method called? 
- If the activity is explicitly told to finish, 
- when you rotate the display, and if the System is struggling for resources would temporarily destroy it.

#### What is EventBus?
- EventBus is third party library that simplifies the communication between components (Activities, Fragments, and background threads)
- observer pattern
- decouples event senders and event receivers (components dont have to know about each other)

##FRAGMENT:

#### What is a FragmentManager? 
Is the abstract class used to manage Fragments within an Activity.

#### What is the difference between an Activity and a Fragment? 
- A Fragment is a piece of an application’s user interface or behavior that can be placed in an Activity, that is it requires an Activity to exist. 
- Whereas an Activity can exist by itself.

#### Headless Fragment?

#### What is the difference between onCreateView() and onActivityCreated()? 
The implementation of any action code can be optional in the onCreateView method this is because there is actually no need to always have an UI for every fragment, yet the onActivityCreated method is always the point in the Fragment’s lifecycle from which you can start referencing and using UI elements, gathering information that may be used to be persisted throughout the application task flow.

#### How to get the FragmentManager from an Activity that is using the support libraries? 
By importing the FragmentManager class from the android.support.v4.app package.

####What method do you use to create the UI in an Activity and in a Fragment? 
onCreate() - Activity onCreateView() - Fragment (which is called after the onCreate())

####What is the Fragment lifecycle? 
The Fragment lifecycle is the set of callback methods called whenever a Fragment is instantiated, since they have their properties of their own, its lifecycle is similar to that of the Activities however they have different states that can be addressed and managed in those callback methods.

####How to support multi-screen devices? 
Through the use of different resources designed to address the different sizes and densities available: layouts and bitmap densities.

#### How to communicate between a Activity and a Fragment? 
Through interface declared in the fragment class and implemented by the parent Activity class. There is also the possibility to use a third party library which implements the subscribe and publish pattern, that simplifies the communication process, e.g. EventBus.

#### How to communicate between 2 fragments? 
Through interface declared in the Fragment A class and implemented by the parent Activity class, then the Activity will update/notify Fragment B class, which it has also defined an Interface. There is also the possibility to use a third party library which implements the subscribe and publish pattern, that simplifies the communication process, e.g. EventBus.

#### Why use an interface in a Fragment to communicate to the hosting Activity? 
Because it removes any dependency between Activities and Fragments. Remembering that Fragments can be modularized and re-used in other activities of an app, the use of an Interface provides a standard way of enabling communication for any activity that desires to send or get data from/to a fragment it simply has to implement the defined callback interface of the fragment.

## Service
#### What is a bound service?
#### When would you use an intent service?
#### What thread does a regular service run on?
#### What is a worker thread?

## Broadcast Recievers

## Content Providers

## Intent & Intent Filters
#### What is serializable?
#### What is a Parcelable? Why is it better than a Serializable?
•	Serializable - uses reflection
•	Parcelable -

## Threads

## REST

## Testing

#### What is dependency injection? Why is it useful?
#### How did you do testing in your previous app?
#### Have you worked with any third party framework for Analytics?
#### Did you work on the HTTP clients there in your previous project?

#### How do you set up MVP?
#### Did you do any Material Design improvements over there?
####What is an Intent?

####How is an Activity different from a Fragment?
####Talk to me about a challenging situation you've had.
####What have you been doing lately?
####Have you done streaming video?
####What players have you used?
####You have also used exoPlayer?
####What are the differences between the list view and the recycler view?

####What would you do to keep the data in a SQLite database when updating an app?
####Have you used external libraries to access an SQLite database?
####What is an intent?

#### What are the launch modes in Android?

#### What other mechanisms are there for managing the back-stack?

#### Does the android support package implement Fragment or Fragment Activity?

#### What is the difference between a hash map and a set?

#### Can you use the same key more than once in a hash map?

#### Why would you use the same key more than once in an activity?

#### How would you then manage the backstack?

##### What is the difference between a content value and a cursor?

#### What libraries have you used for rest?

#### Do you like any of them better than the others?

#### Do you have any experience with payment gateways?




#### What are the things you think about when building an AN app from scratch?

#### You mentioned design patterns, which ones have you used often?

#### Talk more about the Singleton design pattern.

#### What challenges exist in implementing Singleton in Java?

#### Tell me more about challenges with Multithreading with Singleton.

#### So, you’d use the constructor method or some other method?

#### You also mentioned factory pattern – tell me why you’d use this…

#### What have you used for dependency injection?

#### Talk about fragments – have you used those?

#### What are some of the advantages?

#### Speaking of MVP – can you tell me more about this design pattern?

#### How strict do you hold to presenters – using it mostly all the time or only some things?

#### Let’s say you were working with a legacy code base that someone else had written, they didn’t follow best practices (didn’t use dependency injection, no unit testing, no MVP usage), how would you try to implement those things?

#### Talk about 3rd party libraries, why you use them, etc…

#### Let’s say you wanted to do the bare minimum of 3rd party libraries you thought was a good idea – let’s say there is a requirement in the business stating you can’t use certain ones – which ones would you really press to use – which are the most critical?

#### Let’s talk about the networking libraries – can you maybe explain how you might implement networking library manually… Caching – how would you implement caching?

#### Let’s go back to Singleton, let’s say you had one and you wanted to store a context in this Singleton – what problems might I face with this?

#### How would you handle those memory issues then (or avoid memory leaks)?

#### How do you structure communication between the fragments, activities, etc.?

#### RXJava – can you tell me about this and how it works?

#### Talk about databases and storage in general in AN… How would you go about storing sensitive data in your app?

#### Let’s say you can’t use SQLCipher, but you wanted the same functionality, how would you do this? Unit testing – talk about your experience with this, and what amount of effort/investment do you place into unit testing?


#### What is Dagger?

#### Why is dependency injection helpful? Let’s say that I want to build an SDK to download the information from the server, how would you set up dependency injection?

#### What is mocking?

#### Why is Dagger 2 faster than Dagger 1?

#### Talk to me about the Activity lifecycle? When does each callback get triggered?

#### What is the difference between the Fragment lifecycle and the Activity lifecycle?

#### When will each callback in the Fragment lifecycle be called?

#### What are the Git commands that you know?

#### What is the differences between git rebase and git merge?

#### What are the differences between RecyclerView and ListView?

#### What are the Layout Managers that we have?

#### What is Volley?

#### What are the differences between Volley and Retrofit? How do you like Retrofit?

#### How is the proxy pattern implemented in Retrofit? What are the HTTP verbs?

#### What is the difference between POST and PUT?

#### How do you implement security in your app?

#### Tell me step by step how to set up SQLCipher?

#### The answer for the SQLCipher thing is here: http://loopcupcakes.com/2017/02/20/minimal-sqlcipher/

#### Tell me about your last app

#### Which Android studio version are you using?

#### How many apps on store?

#### Can you tell me what are the new features in Android Nougat?

#### What is the Picture-In-Picture mode?

#### What is the task affinity?

#### How to prevent other apps from deleting contents from your content provider?

#### Talk to me about Asynctask disadvantages.

#### Tell me how do I set up the Fingerprint authentication?

#### http://loopcupcakes.com/2017/01/27/fingerprint-authentication/

#### What is the startID in onStartCommand()?

#### What is the difference between AOT and JIT?

#### What third party libs have you used for Logging?

#### Which synchronization practices do you prefer?

#### Explain the Android Architecture and its key components?

		4 levels of
		The Android architecture is a stack of technology that allows an application to
		run on a android OS. There are 5 layers and 6 component
Linux Kernel - bottom layer
Libraries & ART
Java framework API
Application - top layer

#### What is a Context and what is it used for?
1. Activity context: 
- A context is an abstract class, from which Activity and Service derived from  
- It gives you access to resources, allow components to communicate through messages and gives information about the app environment.
2. Application Context: exist for the lifetime of the application

#### What are the differences between a list view and a recycler view? 
The recycler view: 
- conforms to Material Design 
- Animation 
- Decoration 
- Enforces view-holder design pattern 
- uses the layout manager

#### What is a ContentProvider and what is it typically used for? 
Content providers is one of the 4 key component of Android. It allows interprocess communication. It can be used to manage a set of shared application data. It is used for accessing information from other applications, for example contacts. 

#### How is automated testing done in android and what are the native tools? unit testing (Junit) and instrumental test (expresso, mockito) one an emulator 

#### What is the relationship between the life cycle of an AsyncTask and an Activity? 
What problems can this result in? How can these problems be avoided? When an activity is destroyed the AsyncTask continues to execute. The thread becomes and orphan thread and is a problem when the Asynctask wants to return the result. To avoid this problem, stop the running operation on the onDestroy or on onStop callbacks, or you can use a headless fragment, doesn’t destroy the activity when the context change, eventbus, - When change orientation or change language, change in configuration, activity recycles. 

#### What is new for developers in each of the follonwing:
- Lollipop - material design
- Marshmallow - runtime permissions
- Nougat - split screen, new compiler (Jack and jill)

#### What is an Intent? What are three common uses of an Intent? 
2 types - implicit and explicit Intent - allows a way for communicated between the 4 major android components. It can store extras and bundles to be passed between activities. It is also defined by its action and categories. The 3 common applications are: 1. start an activity 2. starts service 3. receive a broadcast 4. bind to a service 

#### What are the different types of Services? 
regular service (started & bound) intent service What is the difference between a fragment and an activity? Explain the relationship between the two? The activity hosts the fragment, the activity can be used to communicate between multiple fragments. The lifecycle of the fragment is closely related to the activity lifecycle, unless it is headless.

#### Explain the lifecycle of Services 
There are 2 types of life cycles for Services, when it is bound an when it isn’t. A regular service can live on indefinitely and a bound service is destroyed with its bound component. started: - onCrea

		bound
		
If your instrumentation test is not running reliably, what might be the cause and how would you fix it?
		animation will cause problems, go to setting and turn off the
Describe the use of resources in Android
		Android studio can use the resources to decided on the most appropriate 			display depending on the users preference. Default resources and specific 			resources
Different resources are used depending on the state of the phone, for example: landscape view, languages, left to right view, night mode
What is difference between Serializable and Parcelable ? Which should you use in Android?
Serializable is an interface that is a slower process that uses reflection, creating many temporary objects, thats belong java sdk
Paracelable is a faster method for marshaling objects, it is typically preferred over serializing, that belongs to android sdk, needs to implement some methods.

####What is the difference between Service and IntentService? How is each used?
		A service uses the main thread, depends: bound until the bound thing ends. 				started - when its called stop() or stops self, and also depends on
				- sticky
				- nonstick
				- restart
		An intent service uses worker thread to perform the operation, runs until it finishes
How would you make an app so that it displays well in all the different screens and device types? Create difference layouts for difference sizes Different icons Using fragments Material design and using density independent pixels will help to standardize the look and feel of all different devices and screen sizes, SPs for font sizes Give me an example of when SQLite is typically used on an App? SQLite is a lightweight database that that is typically use for simple, linear data. Storing the higher scores for each level for each player in a gaming app can use a SQLite. structured data locally cacheing What are some things you should avoid doing in a main thread? Long running activities should be avoided such as: - connection calls - network - uploading/downloading data (file io) What is inversion of control and what tools have you used for this purpose?

What is an adapter and when would you use it? Design pattern - recyclerView, ListView connecting An Adapter is needed when using recycler or list view. It is used to bind information from a list of objects to a view. What are intent filters and how does Android use them? intent filters are declared in the manifest and are used when an implicit intent is made. When an intent filter is declared for an specific action it is saying that it is able to handle this type of action at install time. OS see if there are component that perform that action Why is it important to keep your Android keystore safe? for security reasons, and to keep the integrity of the application - When using a sign APK, you need a keystore. It is how is identified in the android play store. Need the same keystone for user update \ What are the common HTTP verbs and what is each one used for POST - sending information (parameter in the body) GET - requesting information (parameter in the url) DELETE - deleting information PUT - changing information (updating) How is HTTP structured? Explain. request/response protocol is stateless Header - the request Body - additional information - optional
