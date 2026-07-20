#!/bin/bash
MOD_ID=$1
ARTIFACT_NAME=$2

if [ -z "$MOD_ID" ] || [ -z "$ARTIFACT_NAME" ]; then
  echo "Usage: ./update-packwiz-nightly.sh <mod_id> <artifact_name>"
  exit 1
fi

# Format the dynamic nightly download zip URL framework
REPO_URL="https://nightly.link/${GITHUB_REPOSITORY}/artifacts/${ARTIFACT_NAME}.zip"

# Move execution context directly into the Modpack workspace directory
cd Modpack

# Safely check execution path permissions for your pre-compiled binaries
chmod +x .bin/packwiz

# Remove tracking metadata to guarantee an explicit programmatic rewrite
if [ -f "mods/${MOD_ID}.pw.toml" ]; then
  echo "Purging old tracking schema for ${MOD_ID}..."
  rm -f "mods/${MOD_ID}.pw.toml"
fi

# Natively add via external URL link using your local bin executable
./.bin/packwiz url add "$MOD_ID" "$REPO_URL"

echo "Packwiz tracker successfully established for $MOD_ID."