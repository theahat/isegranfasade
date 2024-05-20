#!/bin/bash

# Get the highest tag version (ignoring prefixes like "v")
version=$(git describe --abbrev=0 --tags | sed 's/^v//' )

# Split the version into an array by "."
IFS="." read -ra version_parts <<< "$version"

# Check if there's a version number (avoids errors on initial tag)
if [[ ! -z "$version" ]]; then
  # Get major, minor, and patch numbers (default to 0 for initial tag)
  major=${version_parts[0]:=0}
  minor=${version_parts[1]:=0}
  patch=${version_parts[2]:=0}

  # Increment patch by default, handle major/minor with flags in commit message
  if grep -q '#major' <<< $(git log --format=%B -n 1 HEAD); then
    echo "Update major version"
    major=$((major + 1))
    minor=0
    patch=0
  elif grep -q '#minor' <<< $(git log --format=%B -n 1 HEAD); then
    echo "Update minor version"
    minor=$((minor + 1))
    patch=0
  else
    echo "Update patch version"
    patch=$((patch + 1))
  fi

  # Create the new tag version
  new_version="v$major.$minor.$patch"

  # Check if the latest commit already has a tag
  git_commit=$(git rev-parse HEAD)
  needs_tag=$(git describe --contains "$git_commit")

  if [[ -z "$needs_tag" ]]; then
    echo "Creating tag: $new_version"
    git tag "$new_version"
    git push --tags origin
  else
    echo "Skipping: Latest commit already has a tag."
  fi
else
  echo "No previous tags found. Creating initial tag (v0.0.1)."
  git tag v0.0.1
  git push --tags origin
fi