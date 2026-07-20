#!/bin/bash
MOD_ID=$1          # e.g., "avalitheme"
MOD_NAME=$2        # e.g., "Avali Theme"
JAR_SOURCE_DIR=$3  # e.g., "build-outputs/theme"

BIN_DIR="Modpack/.bin"
MODS_DIR="Modpack/mods"
mkdir -p "$BIN_DIR"

# Locate the production jar
JAR_FILE=$(find "$JAR_SOURCE_DIR" -maxdepth 1 -name "*.jar" ! -name "*-sources.jar" ! -name "*-dev.jar" ! -name "*-api.jar" | head -n 1)

if [ -z "$JAR_FILE" ]; then
  echo "Error: Could not locate a valid production .jar in $JAR_SOURCE_DIR"
  exit 1
fi

FILENAME=$(basename "$JAR_FILE")
HASH=$(sha256sum "$JAR_FILE" | awk '{print $1}')

# Copy the binary straight into the ignored .bin folder
cp "$JAR_FILE" "$BIN_DIR/$FILENAME"

# Generate the explicit .pw.toml format pointing to the ignored folder
cat <<EOF > "$MODS_DIR/${MOD_ID}.pw.toml"
name = "$MOD_NAME"
filename = "$FILENAME"
side = "both"

[download]
url = ".bin/$FILENAME"
hash-format = "sha256"
hash = "$HASH"
EOF

echo "Successfully generated $MODS_DIR/${MOD_ID}.pw.toml and staged binary in .bin/."