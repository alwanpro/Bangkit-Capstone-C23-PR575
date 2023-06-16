## How to run

1. Install NodeJS, yarn, and PostgreSQL
2. Create new database inside postgresql named `nutriku-dev`
3. Execute SQL script inside `configs`
4. Copy environment variables into `.env`
5. Install dependencies

```diff
yarn install
```

6. Start the server

```diff
yarn start
```

7. Run `node utils/migrations.js` to add new foods data from csv

## Documentation links:

https://documenter.getpostman.com/view/27921988/2s93shypMN
