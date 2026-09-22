# Cody — Build directly on Android

This project is arranged so it can be built on an Android phone using a Java/Gradle environment such as Termux.

## Recommended setup

Install a trusted Android terminal/build environment and make sure it provides:

- Java 17
- Gradle 8.x compatible with Android Gradle Plugin 8.7.x
- Android SDK Platform 35
- Android SDK Build-Tools 35.x

Then extract this project and run:

    gradle :app:assembleDebug

The APK will be created at:

    app/build/outputs/apk/debug/app-debug.apk

## If Gradle is not installed

Use the Gradle version supported by your Android build environment. The Android Gradle Plugin is declared in the root build.gradle.kts.

## Install the APK

After a successful build, install `app-debug.apk` with Android's package installer or:

    adb install app/build/outputs/apk/debug/app-debug.apk

If ADB is not available on the phone, open the APK with a file manager and allow installation from that source when Android asks.

## Important

This project is source code. It is NOT itself an APK.

The first version intentionally uses Android's built-in SpeechRecognizer and TextToSpeech, so it does not require an AI API key just to test the basic voice/action system.

The AI planner comes in a later version.
