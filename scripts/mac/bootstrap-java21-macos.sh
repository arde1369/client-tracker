#!/usr/bin/env zsh
set -euo pipefail

# Bootstrap local shell to use Java 21 for this project.
JAVA_HOME_21="$(/usr/libexec/java_home -v 21 2>/dev/null || true)"
if [[ -z "${JAVA_HOME_21}" ]]; then
  echo "Java 21 was not found on this machine."
  echo "Install JDK 21 (for example: Temurin 21) and run this script again."
  exit 1
fi

ZSHRC="${HOME}/.zshrc"
START_MARK="# >>> client-tracker-java21 >>>"
END_MARK="# <<< client-tracker-java21 <<<"
TMP_FILE="$(mktemp)"

if [[ -f "${ZSHRC}" ]]; then
  awk -v start="${START_MARK}" -v end="${END_MARK}" '
    BEGIN { skip=0 }
    $0 == start { skip=1; next }
    $0 == end { skip=0; next }
    skip == 0 { print }
  ' "${ZSHRC}" > "${TMP_FILE}"
else
  : > "${TMP_FILE}"
fi

cat >> "${TMP_FILE}" <<EOF

${START_MARK}
export JAVA_HOME="${JAVA_HOME_21}"
if [[ ":\$PATH:" != *":\$JAVA_HOME/bin:"* ]]; then
  export PATH="\$JAVA_HOME/bin:\$PATH"
fi
${END_MARK}
EOF

mv "${TMP_FILE}" "${ZSHRC}"

cat <<EOF
Bootstrap complete.

Applied Java 21 to: ${ZSHRC}
JAVA_HOME set to: ${JAVA_HOME_21}

Next steps:
1) source "${ZSHRC}"
2) java -version
3) mvn -v
EOF
