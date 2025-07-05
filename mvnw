#!/bin/sh

if [ -x "$(command -v mvn)" ]; then
  mvn "$@"
else
  echo "Maven is not installed" >&2
  exit 1
fi
