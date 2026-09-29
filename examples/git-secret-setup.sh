#!/bin/bash

set -xeuo pipefail

cd "$(dirname "$0")/../.."

rm -rf ai-workshop-repos
mkdir ai-workshop-repos
cd ai-workshop-repos

git init --bare -b main origin
git clone origin webshop
cd webshop

echo "##### commit 1 - start of the project #####" > /dev/null
echo "# Webshop" > README.md
git add README.md
git commit -m "Add README"

echo "##### commit 2 - database configuration, with the password in plain text #####" > /dev/null
cat > application.properties <<'EOF'
spring.datasource.url=jdbc:postgresql://db.webshop.local:5432/webshop
spring.datasource.username=webshop
spring.datasource.password=Welkom123
EOF
git add application.properties
git commit -m "Add database configuration"

echo "##### commit 3 - an unrelated change on top #####" > /dev/null
echo "server.port=8081" >> application.properties
git commit -am "Run on port 8081"

echo "##### commit 4 - the password is replaced, but still in the history #####" > /dev/null
cat > application.properties <<'EOF'
spring.datasource.url=jdbc:postgresql://db.webshop.local:5432/webshop
spring.datasource.username=webshop
spring.datasource.password=${DB_PASSWORD}
server.port=8081
EOF
git commit -am "Read database password from environment variable"

git push -u origin main
cd ..

echo "##### a colleague clones the old history and has one commit not pushed yet #####" > /dev/null
git clone origin colleague
cd colleague
echo "Set the DB_PASSWORD environment variable before starting." >> README.md
git commit -am "Document DB_PASSWORD"
cd ../webshop

git log --oneline --graph --all

echo "##### the commits that add or remove the password #####" > /dev/null
git log --oneline -S Welkom123
