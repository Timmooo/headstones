#!/bin/bash
set -euo pipefail

# Paths
BUILD_JAR="build/libs/Headstones-*.jar"
DEPLOY_DIR="/home/amp/.ampdata/instances/WestMinecraft01/Minecraft/plugins"

# Copy the built jar into the AMP instance plugins dir
echo "Deploying latest build to $DEPLOY_DIR ..."
sudo cp $BUILD_JAR "$DEPLOY_DIR/"

# Grab the actual filename (in case version changes)
LATEST_JAR=$(ls -t $BUILD_JAR | head -n1)
DEPLOYED_JAR="$DEPLOY_DIR/$(basename "$LATEST_JAR")"

# Fix ownership so AMP can manage it
sudo chown amp:amp "$DEPLOYED_JAR"

echo "Deployment complete: $DEPLOYED_JAR"
