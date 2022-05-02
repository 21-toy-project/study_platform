#!/bin/sh

CURRENT=$(dirname "$0")
ROOT=$(dirname "$CURRENT")

PRE_COMMIT_FILE="$CURRENT/pre-commit"
GIT_HOOKS="$ROOT/.git/hooks"

cp "$PRE_COMMIT_FILE" "$GIT_HOOKS"

chmod +x "$GIT_HOOKS/pre-commit"
