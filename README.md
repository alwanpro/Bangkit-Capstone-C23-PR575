# Bangkit 2023 Capstone Team (C23-PR575) : Nutriku

![Logo](https://github.com/alwanpro/Bangkit-Capstone-C23-PR575/blob/main/Nutriku.png?raw=true)


A Capstone project focuses on detect food, display food details, and nutri score.

## Our Team:
|           Name             |  Bangkit-ID  |    Learning Path   |         University         |
|----------------------------|--------------|--------------------|----------------------------|
| Thomas Ferdinand           |  C017DSX0620 | Cloud Computing    | Institut Teknologi Bandung |
| Muhammad Fikri Pratama     |  C360DSX3343 | Cloud Computing    | Universitas Telkom         |
| Yusuf Alwansyah Hilmy      |  M017DSX2332 | Machine Learning   | Institut Teknologi Bandung |
| Ikram Tauffiqul Hakim      |  M360DSX2328 | Machine Learning   | Universitas Telkom         |
| Muhammad Rifky Muthahhari  |  A017DSX1165 | Mobile Development | Institut Teknologi Bandung |
| Sachi Kirana Hera Singh    |  M181DSY0377 | Machine Learning   | Universitas Indonesia      |

  
# Clone Repository
1. To view the details of each path, please use the branch feature

```
https://github.com/alwanpro/Bangkit-Capstone-C23-PR575/
```

## CC

### How to run backend (local)

1. Install [Node.js](https://nodejs.org/en/download), [yarn](https://classic.yarnpkg.com/lang/en/docs/install/#windows-stable), and [PostgreSQL](https://www.postgresql.org/download/)
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
9. Documentation [link](https://documenter.getpostman.com/view/27921988/2s93shypMN)
