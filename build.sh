#!/usr/bin/env bash
set -e

# 1. Java Check: Verify Java 25 is available in PATH, fall back to $JAVA_HOME
if command -v java >/dev/null 2>&1; then
    JAVA_CMD="java"
elif [ -n "$JAVA_HOME" ] && [ -x "$JAVA_HOME/bin/java" ]; then
    JAVA_CMD="$JAVA_HOME/bin/java"
else
    echo "ERROR: Java 25 not found in PATH or JAVA_HOME" >&2
    exit 1
fi

echo "Using Java: $($JAVA_CMD -version 2>&1 | head -n 1)"

# 2. Gradle Wrapper Check: If wrapper missing, generate it with system gradle
if [ ! -f "./gradlew" ]; then
    echo "Gradle wrapper not found. Generating with system gradle..."
    if command -v gradle >/dev/null 2>&1; then
        gradle wrapper
    else
        echo "ERROR: No gradle wrapper and no system gradle found" >&2
        exit 1
    fi
fi

# 3. Build Execution: Try wrapper build, fall back to clean build
echo "Building with Gradle wrapper..."
if ./gradlew build; then
    echo "Build succeeded."
else
    echo "Build failed, attempting clean build..."
    ./gradlew clean build
fi
