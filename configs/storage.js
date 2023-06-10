import { Storage } from '@google-cloud/storage';

export const storage = new Storage({
  credentials: {
    private_key: process.env.PRIVATE_KEY,
    client_email: process.env.CLIENT_EMAIL,
    client_id: process.env.CLIENT_ID,
    project_id: process.env.PROJECT_ID,
  },
});
