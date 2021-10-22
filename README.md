# Android-Architecture-Sample
 Android Architecture Study with MVVM and MVI
 Using DI(Hilt) + Retrofit + Coroutine + Moshi + AAC

* Stack
    * [100% Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
    * [Retrofit](https://square.github.io/retrofit/)
    * [Jetpack](https://developer.android.com/jetpack)
        * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/)
        * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
        * [Room](https://developer.android.com/jetpack/androidx/releases/room)
    * [Hilt](https://dagger.dev/hilt/)
    * [Glide](https://github.com/coil-kt/coil)
    * [Lottie](http://airbnb.io/lottie) - ing...
* Architecture
    * [MVVM](https://en.wikipedia.org/wiki/Model–view–viewmodel)
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
    * [Android KTX](https://developer.android.com/kotlin/ktx)
* CI - ing...
    * [GitHub Actions](https://github.com/features/actions)
* Testing - ing...
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit 5](https://junit.org/junit5/) via
      [android-junit5](https://github.com/mannodermaus/android-junit5))
    * [UT Tests](https://en.wikipedia.org/wiki/Graphical_user_interface_testing) ([Espresso](https://developer.android.com/training/testing/espresso))
    * [Mockk](https://mockk.io/)
    * [Truth](https://truth.dev)
* UI
    * [Material design](https://material.io/design)
* Lint
    * [Detekt](https://github.com/arturbosch/detekt#with-gradle)
    
Example
-------

* Retrofit Example
    * JsonService.kt
    * DataRepository.kt
    * DataRepositoryImpl.kt
    * GetPostsUseCase.kt
    * MainViewModel.kt
* Observe Example (LiveData vs StateFlow)
    * MainViewModel.kt
    * MainActivity.kt
* DiffUtil Example
    * PostAdapter.kt


License
-------
```
MIT License

Copyright (c) 2021 dudgns0507

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```