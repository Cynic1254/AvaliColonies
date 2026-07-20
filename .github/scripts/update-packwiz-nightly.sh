#!/bin/bash
MOD_ID=$1
JAR_SOURCE_DIR=$2

if [ -z "$MOD_ID" ] || [ -z "$JAR_SOURCE_DIR" ]; then
  echo "Usage: ./update-packwiz-nightly.sh <mod_id> <jar_source_dir>"
  exit 1
fi

TARGET_DIR="Modpack/jars"
mkdir -p "$TARGET_DIR"

# Clean out the old binary iteration for this specific mod
rm -f "$TARGET_DIR"/"$MOD_ID"*.jar

# Copy the fresh compiled jar into the LFS-tracked directory
cp "$JAR_SOURCE_DIR"/*.jar "$TARGET_DIR"/

echo "Successfully staged $MOD_ID binary into LFS-tracked Modpack folder."