# Pikkel
An alternative to IcePick for Kotlin.

```kotlin
class MainActivity : AppCompatActivity(), Pikkel by PikkelDelegate() { // Implement Pikkel interface with PikkelDelegate class delegation.

    var data by state<String?>(null) // This will be automatically saved and restored

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState) // Saved states are restored here by Pikkel
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState) // Save states here by Pikkel
    }
}
```

Download
----

[![](https://jitpack.io/v/yamamotoj/Pikkel.svg)](https://jitpack.io/#yamamotoj/Pikkel)

Add the jitpack maven repository to your build.gradle file:

```gradle
repositories {
  maven { url "https://jitpack.io" }
}
```

```gradle
dependencies {
  compile 'com.github.yamamotoj:pikkel:0.3.2'
}
```

You can also copy Pikkel.kt into your source tree.

Licence
----

```
Copyright 2016 Jumpei Yamamoto

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
