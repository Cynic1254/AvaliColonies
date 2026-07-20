#!/bin/bash
TAG_VERSION=$1

if [ -z "$TAG_VERSION" ]; then
  echo "Error: No tag version supplied to safety script."
  exit 1
fi

# Strip prefixes to get standard semantic notation
CLEAN_VERSION=$(echo "$TAG_VERSION" | sed -e 's/^v//' -e 's/^release-//')

if [[ ! "$CLEAN_VERSION" =~ ^[0-9]+\.[0-9]+\.[0-9]+ ]]; then
  echo "Error: Tag '$TAG_VERSION' does not match a valid semantic version layout."
  exit 1
fi

echo "Valid Tag Detected. Updating workspace configurations to version: $CLEAN_VERSION"

# Target the properties file in the correct subfolder
PROPERTIES_FILE="CustomMods/gradle.properties"
sed -i "s/avalitheme.mod_version=.*/avalitheme.mod_version=$CLEAN_VERSION/g" "$PROPERTIES_FILE"
sed -i "s/connector.mod_version=.*/connector.mod_version=$CLEAN_VERSION/g" "$PROPERTIES_FILE"