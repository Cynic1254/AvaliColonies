#!/bin/bash
TAG_VERSION=$1

if [ -z "$TAG_VERSION" ]; then
  echo "Error: No tag version supplied to safety script."
  exit 1
fi

CLEAN_VERSION=$(echo "$TAG_VERSION" | sed -e 's/^v//' -e 's/^release-//')

if [[ ! "$CLEAN_VERSION" =~ ^[0-9]+\.[0-9]+\.[0-9]+ ]]; then
  echo "Error: Tag '$TAG_VERSION' does not match a valid semantic version layout."
  exit 1
fi

echo "Valid Tag Detected. Updating workspace configurations to version: $CLEAN_VERSION"

# Update subproject property files
if [ -f "theme/gradle.properties" ]; then
  sed -i "s/mod_version=.*/mod_version=$CLEAN_VERSION/g" "theme/gradle.properties"
fi

if [ -f "connector/gradle.properties" ]; then
  sed -i "s/mod_version=.*/mod_version=$CLEAN_VERSION/g" "connector/gradle.properties"
fi