# Pikkel
An alternative to IcePick for Kotlin.

```kotlin:MainActivity.kt
class MainActivity : AppCompatActivity(), Pikkel by PikkelDelegate() {

    var data by state<String?>(null) // This will be automatically saved and restored

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)
        /*  ... */
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState)
    }
}
```
