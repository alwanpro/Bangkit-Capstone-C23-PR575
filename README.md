## How to run

1. Install Node.js, yarn, and PostgreSQL
2. Create new database inside PostgreSQL named `nutriku-dev`
3. Execute SQL schema script inside `configs/schema.sql` and foods seeds inside `configs/migrations.sql`
4. Copy `.env.example` into `.env` and configure the environment variables
5. Install dependencies

```diff
yarn install
```

6. Start the server

```diff
yarn start
```

7. Open `http://localhost:8000/docs` to see list of endpoints
8. Run and configure files inside `node utils/migrations.js` to add new foods data from csv stored inside `docs/data_makanan.csv`

## Documentation links:

https://documenter.getpostman.com/view/27921988/2s93shypMN
