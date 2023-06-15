## How to run

1. Install NodeJS, Yarn, and postgresql
2. Create new database inside postgresql named `nutriku-dev`
3. Execute SQL script inside `configs`
4. Copy environment variables into `.env`
5. Install dependencies with `yarn install`
6. Run the server `yarn start`
7. Run `node utils/migrations.js` to add new foods data from csv
