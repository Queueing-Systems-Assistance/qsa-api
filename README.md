# QSA API ![CircleCI](https://img.shields.io/circleci/build/github/Queueing-Systems-Assistance/qsa-api/master)

### Project description

This service is the door, acts as a facade for the QSA architecture.
It creates a main entry point using GraphQL to call the underlying services such as calculator, formula-handler, i18n-service, etc. 

### Endpoints

- For different locales, set the `Accept-Language` header value (en_US, hu_HU, etc.)

#### /graphql

- Accepts only POST request
- Need a valid GraphQL body
- Schema found under `qsa-api-server/src/resources/schema.graphqls`
