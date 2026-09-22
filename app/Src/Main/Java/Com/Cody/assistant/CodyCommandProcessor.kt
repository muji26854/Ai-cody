package com.cody.assistant

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.speech.tts.TextToSpeech
import java.util.Locale

class CodyCommandProcessor(
    private val context: Context,
    private val tts: TextToSpeech
) {
    fun handle(raw: String) {
        val command = raw.trim().lowercase(Locale.US)

        when {
            command == "go back" || command == "back" -> {
                CodyAccessibilityService.instance?.goBack()
                speak("Going back.")
            }

            command == "go home" || command == "home" -> {
                CodyAccessibilityService.instance?.goHome()
                speak("Going home.")
            }

            command.contains("open youtube") -> {
                openApp("com.google.android.youtube", "YouTube")
            }

            command.contains("open chrome") -> {
                openApp("com.android.chrome", "Chrome")
            }

            command.startsWith("search for ") -> {
                val query = raw.substringAfter("search for ").trim()
                val url = Uri.parse("https://www.google.com/search?q=" + Uri.encode(query))
                context.startActivity(Intent(Intent.ACTION_VIEW, url).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                speak("Searching for $query.")
            }

            command.contains("screenshot") -> {
                speak("Screenshot control will be added in the next build.")
            }

            command.contains("scroll down") -> {
                CodyAccessibilityService.instance?.scrollDown()
                speak("Scrolling down.")
            }

            command.contains("scroll up") -> {
                CodyAccessibilityService.instance?.scrollUp()
                speak("Scrolling up.")
            }

            else -> {
                speak("I heard: $raw. I don't have an action for that command yet.")
            }
        }
    }

    private fun openApp(packageName: String, name: String) {
        val launch = context.packageManager.getLaunchIntentForPackage(packageName)
        if (launch == null) {
            speak("$name is not installed.")
            return
        }
        launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(launch)
        speak("Opening $name.")
    }

    private fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "CODY_REPLY")
    }
}
