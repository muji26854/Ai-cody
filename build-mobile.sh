#!/data/data/com.termux/files/usr/bin/bash
set -e
echo "Building Cody debug APK..."
gradle :app:assembleDebug
echo
echo "APK:"
echo "$PWD/app/build/outputs/apk/debug/app-debug.apk"
