#!/usr/bin/env bash

echo "mongoimport (mongodb_import.sh): Data import in JSON format will start..."

LAST_VERSION_DIRECTORY="$(ls -d /db_test_data/*/ | tail -n 1)"
MONGO_DATABASE="bookshop"

for FILE in "${LAST_VERSION_DIRECTORY}"*.json
do
  IFS='_'
  read -ra ARRAY <<< "$(basename "$FILE")"
  COLLECTION_NAME=${ARRAY[0]}

  mongoimport --jsonArray --db "$MONGO_DATABASE" --drop --collection "$COLLECTION_NAME" --file "$FILE"
done

echo "mongoimport (mongodb_import.sh): Data import in JSON format is completed."